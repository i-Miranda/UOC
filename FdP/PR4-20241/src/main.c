/*
* File: main.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR4
*/

/* System header files */
#include "grades.h"

/*Constant for file reading */
#define MAX_FILE_NAME 100+1


/* main */
int main(int argc, char **argv)
{
	tStudentsTable studentsTable;
	tStudentsTable honorsTable;
	int i, studentId;
	
	char filename[MAX_FILE_NAME];
	/* passed is a better word rather than approved, but in order to follow the rules I won't change it. */
	float approvedPercent = 0.0;
	
	bool isRead = false;
    bool found = false;
	
	/* load data from file*/ 
    printf("LOAD DATA FROM FILE. ENTER FILE NAME >>\n");
    scanf("%s", filename);

    /* Exercise 1 */	
    studentsLoadDataFromFile(filename, &studentsTable, &isRead);	

	/* Data successfully loaded */
	if (isRead) {
		printf ("\nRESULTS:\n");

		for(i = 0; i < studentsTable.nStudents; i++) {
			/* Exercise 2 */
			calculateStudentCaaAndPr(&studentsTable.students[i]);
			/* Exercise 3 */
			calculateFinalMarkAndAbsent(&studentsTable.students[i]);
		}

		/* Exercise 4 */
		writeStudentsTableToFile(&studentsTable, "grades.txt");
		printf("\nSTUDENTS MARKS\n");
		printf("==============\n");
		writeStudentsData(studentsTable);

		/* Exercise 5 */
		sortStudentsTable(&studentsTable);
		printf("\nSTUDENTS SORTED BY FINAL MARK\n");
		printf("=============================\n");
		writeStudentsData(studentsTable);

		/* Exercise 6 */
		approvedPercent = getPassingPercentage(studentsTable);
		/* Again, approved really should be passed.  Not changing it because of DSLab */
		printf("\nPercent students approved: %.2f\n", approvedPercent);

		/* Exercise 7 */
		getHonorsCandidates(&studentsTable, &honorsTable); 
		printf("\nSTUDENTS IN HONOR LIST\n");
		printf("======================\n");
		writeStudentsData(honorsTable);

		/* Exercise 8 */
		printf("\nSELECT STUDENT BY ID >> \n");
		scanf("%d", &studentId);
		found = studentIsHonors(studentId, &honorsTable);
		if (found) {
			printf("STUDENT IN HONOR LIST\n");
		} else {
			printf("STUDENT NOT IN HONOR LIST\n");
		}
	} else {
		/* No data recovered */
		printf("NO STUDENTS RECOVERED\n");	
	}
		
	return 0;
}
