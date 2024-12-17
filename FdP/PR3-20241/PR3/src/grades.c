/*
* File: grades.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR2
*/

#include "grades.h"

/* Exercise 1 */
/* Function to load student data from file */
void studentsLoadDataFromFile(const char* filename, tStudentsTable *studentsTable, bool *isRead)
{
	FILE *fin=0;
	char line[MAX_LINE];
	char buffer[MAX_LINE];
	tStudent newStudent;
	int i;
	
	/* Open the input file */
	if ((fin = fopen(filename,"r")) != NULL) {
		/* Read all the students */
        /* Initializations */
		/* ... */
		while (!feof(fin) && /* ... */) {
			/* Remove any content from the line */
			line[0] = '\0';
			
			/* Read one line (maximum 511 chars) and store it in "line" variable */
			fgets (line,MAX_LINE - 1, fin);
			
			/* Ensure that the string is ended by 0*/
			line[MAX_LINE - 1] = '\0';
			
			if (strlen(line) > 0) {
				/* Obtain the id and name data from file line*/
				sscanf(line, "%d %s %[^\n]s", &newStudent.studentId, newStudent.name, buffer);
	
				/* Read the grades ant its state and add them to the table */
				for (i = 0; i < NUM_ACTIVITIES; i++) {
                    /* Read mark and state of the activity */
                    sscanf(buffer, "%f %u %[^\n]s", &newStudent.activities[i].mark, &newStudent.activities[i].state, buffer);
                    /* Assign activity name */
                    /* ... */
				}	
				
				/* Add student to students table*/
				studentsTable[i] = newStudent;
                /* Increment the counter. */
				studentsTable.nStudents++;
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

/* Exercise 2 */
/* Action to calculate a student's CAA and PR marks */
void	calculateStudentCaaAndPr(tStudent student){
	tActivityType actType;
	int actWeight;
}
/* ... */


/* Exercise 3 */ 
/* Action that returns the type of activity and its weight */
void	getActivityTypeAndWeight(tActivity activity, tActivityType *actType, int *actWeight){
	if (activity.name < PR1){
		*actType = CAA;
		if (activity.name == CAA1){
			*actWeight = CAA1_WEIGHT;
		} else if (activity.name == CAA2){
			*actWeight = CAA2_WEIGHT;
		} else if (activity.name == CAA3){
			*actWeight = CAA3_WEIGHT;
		} else if (activity.name == CAA4){
			*actWeight = CAA4_WEIGHT;
		}
	} else {
		*actType = PR;
		if (activity.name == PR1){
			*actWeight = PR1_WEIGHT;
		} else if (activity.name == PR2){
			*actWeight = PR2_WEIGHT;
		} else if (activity.name == PR3){
			*actWeight = PR3_WEIGHT;
		}
	}
}



/* Exercise 4 */
/* Action to calculate a student's CAA and PR marks */
/* ... */



/* Exercise 5 */ 
/* Action that writes a student's ID, name and grades in both numeric and letter format. */
void writeStudentData (tStudent student, float markCaa, float markPr, int nCaa, int nPr) {
	/* Data output */
	printf("%d\n", student.studentId);
	printf("%s\n", student.name);
	printf("%.2f\n", student.activities[nCaa]);
	printf("%.2f\n", student.activities[nPr]);
	/* ... */
}


/* Exercise 6 */ 
/* Function that test all PR activities are submitted */
bool checkAllPRSubmitted(tStudent student){
	bool allSubmitted = true;
	int	i = 0;

	while (i < NUM_ACTIVITIES)
	{
		if (student.activities[i].state != SUBMITTED){
			allSubmitted = false;
		}
		i++;
	}
	return allSubmitted;
}
