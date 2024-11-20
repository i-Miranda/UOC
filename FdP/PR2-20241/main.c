/*
* File: main.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR2
*/

/* System header files */
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

/*Constants for file reading */
#define MAX_FILE_NAME 100+1
#define MAX_LINE 514
/*****************************/

#define MAX_NAME_LENGTH 20+1
#define MAX_STUDENTS      20	/*	Max. Number of students 					*/
#define NUM_ACTIVITIES     7	/*	Max. Number of activities of the subject	*/

#define MIN_C_MINUS 3.0f		/*	Minimum mark for grade C-	*/
#define MIN_C_PLUS  5.0f		/*	Minimum mark for grade C+	*/
#define MIN_B       7.0f		/*	Minimum mark for grade B 	*/
#define MIN_A       9.0f		/*	Minimum mark for grade A 	*/

#define CAA_WEIGHT 10			/*	Percent weight of each CAA in the final grade	*/
#define PR_WEIGHT  20			/*	Percent weight of each PR in the final grade	*/


/* User defined types */
typedef enum {A = 1, B, C_PLUS, C_MINUS, D} tGrade;
typedef enum {CAA = 1, PR} tActivityType;

typedef struct {
	float activityMark;          /* Activity Mark */
	tActivityType activityType;  /* Activity Type */
}tActivity;

typedef struct {   
    int studentId;                          /* Student ID */               
    char name[MAX_NAME_LENGTH];             /* Student Name */
    tActivity activities [NUM_ACTIVITIES];  /* Student Activities */
} tStudent;

/* Functions prototypes declaration for auxiliary functions */
void studentsLoadDataFromFile(const char* filename, tStudent students[],int *studentCount, bool *isRead);
void writeStudentData (tStudent student, float mark, tGrade grade);
void writeStudentActivities (tStudent student);

/* Functions prototypes declaration for exercises */
float getFinalGPA(tStudent student); /* Exercise 1 */
tGrade getFinalGrade(float finalGPA); /* Exercise 2 */

/* main */
int main(int argc, char **argv)
{
	tStudent students[MAX_STUDENTS];
	int studentCount;
	int studentId;
	int i;
	
	char filename[MAX_FILE_NAME];
	float mark;
	tGrade grade;
	
	bool isRead;
	bool found;
	
	/* load data from file*/ 
    printf("LOAD DATA FROM FILE. ENTER FILE NAME >>\n");
    scanf("%s", filename);	
    studentsLoadDataFromFile(filename, students, &studentCount, &isRead);	
	
	if (isRead) { /* Data successfully loaded */
	
		/* Exercise 3 */
		printf ("\nRESULTS:\n");		
		for(i = 0; i < studentCount; i++){
			mark = getFinalGPA(students[studentCount]);
			grade = getFinalGrade(mark);
			writeStudentData(students[studentCount], mark, grade);	
			printf();
			printf();
		}
		/* ... */
		
		/* Exercise 4 */
		/* Select a student by ID and list all his activities*/
		printf("\nSELECT STUDENT BY ID >>\n");
		scanf("%d", &studentId);
		
		/* Search student by ID */
		/* ... */
		
	}
	else {
		/* No data recovered */
		printf("NO STUDENTS RECOVERED\n");	
	}
		
	return 0;
}

/***********************/
/* Auxiliary functions */

/* Function to load student data from file */
void studentsLoadDataFromFile(const char* filename, tStudent students[], int  *studentCount, bool *isRead)
{
	FILE *fin=0;
	char line[MAX_LINE];
	char buffer[MAX_LINE];
	tStudent newStudent;
	int i;
	
	float activityMarkVector [NUM_ACTIVITIES];
	tActivityType activityTypeVector [NUM_ACTIVITIES];
	
	tActivity activityTmp;

	/* Open the input file */
	if ((fin = fopen(filename,"r")) != NULL) {
		/* Read all the students */
		*studentCount = 0;
		
		while (!feof(fin) && *studentCount < MAX_STUDENTS) {
			/* Remove any content from the line */
			line[0] = '\0';
			
			/* Read one line (maximum 511 chars) and store it in "line" variable */
			fgets (line,MAX_LINE - 1, fin);
			
			/* Ensure that the string is ended by 0*/
			line[MAX_LINE - 1] = '\0';
			
			if (strlen(line) > 0) {
				/* Obtain the data from file line*/
				sscanf(line, "%d %s %[^\n]s", &newStudent.studentId, newStudent.name, buffer);
				for (i = 0; i < NUM_ACTIVITIES; i++){
					sscanf(buffer, "%f %u %[^\n]s", &activityMarkVector[i], &activityTypeVector[i], buffer);
				}
	
				/* Generate the grades and add them to the vector */
				for (i = 0; i < NUM_ACTIVITIES; i++) {
					activityTmp.activityMark = activityMarkVector[i];
					activityTmp.activityType = activityTypeVector[i];
					newStudent.activities[i] = activityTmp;
				}	
				
				/* Add student to students vector*/
				students [*studentCount] = newStudent;
                /* Increment the counter. */
				*studentCount = *studentCount + 1;						
			}
		}
		/* Close the file */
		fclose(fin);
		*isRead = true;
	}
	else {
		*isRead = false;
	}
}

/* Function that writes a student's ID, name and grades in both numeric and letter format. */
void writeStudentData (tStudent student, float mark, tGrade grade) {
	char gradeString [MAX_NAME_LENGTH];
	
	strcpy(gradeString,"");
	
	/* Data process */
	/* Generate the letter grade.*/
	if (grade == A) {
		strcpy(gradeString, "A");
	} else {
		if (grade == B) {
			strcpy(gradeString, "B");
		} else {
			if (grade == C_PLUS) {
				strcpy(gradeString, "C+");
			} else {
				if (grade == C_MINUS) {
					strcpy(gradeString, "C-");
				} else {
					if (grade == D) {
						strcpy(gradeString, "D");
					}
				}
			}
		}
	}
	/* Data output */
	printf("%2d %-20s %5.2f %s\n", student.studentId, student.name, mark, gradeString);	
}

/* Function that writes the evaluations of each activity and its type.*/
void writeStudentActivities (tStudent student) {
	
	int i;
	
	/* Header: grade and type of activity */
	printf("GRADE ACTIVITY (1-CAA, 2-PR)\n");
	
	/* Print all activities */
    for (i = 0; i < NUM_ACTIVITIES; i++) {
        /* Data output */
        printf("%5.2f %d\n", student.activities[i].activityMark, student.activities[i].activityType);
    }
}

/* End auxiliary functions */
/***************************/
/* Exercise 1 */
float getFinalGPA(tStudent student){
	float finalGPA;
	
	finalGPA = 0;
	for(i = 0; i < NUM_ACTIVITIES; i++){
		if (student.activities[i].activityType == CAA){
			finalGPA += student.activities[i].activityMark / (float)CAA_WEIGHT;
		} else {
			finalGPA += student.activities[i].activityMark / (float)PR_WEIGHT;
		}
	}

	finalGPA /= (float)NUM_ACTIVITIES;

	return finalGPA;
}

/* Exercise 2 */
tGrade getFinalGrade(float finalGPA){
	tGrade finalGrade;

	finalGrade = A;
	if (finalGPA < MIN_A)
		finalGrade = B;
	if (finalGPA < MIN_B)
		finalGrade = C_PLUS;
	if (finalGPA < MIN_C_PLUS)
		finalGrade = C_MINUS;
	if (finalGPA < MIN_C_MINUS)
		finalGrade = D;

	return finalGrade;
}
/* ... */


