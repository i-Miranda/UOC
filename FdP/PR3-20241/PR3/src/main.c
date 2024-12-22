/*
* File: main.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR2
*/

/* System header files */
#include "grades.h"

/*Constant for file reading */
#define MAX_FILE_NAME 100+1

/* main */
int main(int argc, char **argv)
{
	tStudentsTable	studentsTable;
	int				i;
	char			filename[MAX_FILE_NAME];
	bool			isRead = false;

	/* Variables stored for actions */
	float			markCaa, markPr;
	int				nCaa, nPr;
	
	/* load data from file*/
    printf("LOAD DATA FROM FILE. ENTER FILE NAME >>\n");
    scanf("%s", filename);

    /* Exercise 1 */
	studentsLoadDataFromFile(filename, &studentsTable, &isRead);

	/* Data successfully loaded */
	if (isRead) { 
		printf ("\nRESULTS:\n");
		printf("ID NAME PEC_MARK PR_MARK N_PEC N_PR ALL_PR?(1=YES)\n");

		/* Only iterate the amount of nStudents in the studentsTable */
		for(i = 0; i < studentsTable.nStudents; i++) {
			/* Exercise 2 */
			calculateStudentCaaAndPr(studentsTable.students[i], &markCaa, &markPr);
			/* Exercise 4 */
			getNActivitiesSubmitted(studentsTable.students[i], &nCaa, &nPr);
			/* Exercise 5 */
			writeStudentData(studentsTable.students[i], markCaa, markPr, nCaa, nPr);
		}
	} else {
		/* No data recovered */
		printf("NO STUDENTS RECOVERED\n");
	}
		
	return 0;
}







