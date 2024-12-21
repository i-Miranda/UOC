/*
* File: grades.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR2
*/

#include "grades.h"

/* Exercise 1 */
/* Function to load student data from file */
void studentsLoadDataFromFile(const char *filename, tStudentsTable *studentsTable, bool *isRead)
{ FILE *fin=0;
	char line[MAX_LINE];
	char buffer[MAX_LINE];
	tStudent newStudent;
	int i;
	tActivityName actName;
	
	/* Open the input file */
	if ((fin = fopen(filename,"r")) != NULL) {
        /* Initializations */
		studentsTable->nStudents = 0;

		/* Read all of the students */
		while (!feof(fin) && studentsTable->nStudents < MAX_STUDENTS) {
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
					if (i == 0){
						actName = CAA1;
					} else if (i == 1){
						actName = CAA2;
					} else if (i == 2){
						actName = CAA3;
					} else if (i == 3){
						actName = CAA4;
					} else if (i == 4){
						actName = PR1;
					} else if (i == 5){
						actName = PR2;
					} else if (i == 6){
						actName = PR3;
					}	
					newStudent.activities[i].name = actName;
				}	
				/* Add student to students table*/
				studentsTable->students[studentsTable->nStudents] = newStudent;
                /* Increment the counter. */
				studentsTable->nStudents++;

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
void	calculateStudentCaaAndPr(tStudent student, float *markCaa, float *markPr){
	tActivityType actType;
	int actWeight;
	int	i;

	/* Initialization before loop */
	i = 0;
	*markCaa = 0.0f;
	*markPr = 0.0f;

	while (i < NUM_ACTIVITIES){
		getActivityTypeAndWeight(student.activities[i], &actType, &actWeight);	
		if (student.activities[i].state == SUBMITTED){
			if (actType == CAA){
				*markCaa += student.activities[i].mark * (float)actWeight/100.0f;
			} else {
				*markPr += student.activities[i].mark * (float)actWeight/100.0f;
			}
		}
		i++;
	}
}

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
void	getNActivitiesSubmitted(tStudent student, int *nCaa, int *nPr){
	tActivityType actType;
	int actWeight;
	int i;

	i = 0;
	*nCaa = 0;
	*nPr = 0;
	while (i < NUM_ACTIVITIES){
		getActivityTypeAndWeight(student.activities[i], &actType, &actWeight);	
		if (student.activities[i].state == SUBMITTED){
			if (actType == CAA){
				(*nCaa)++;
			} else {
				(*nPr)++;
			}
		}
		i++;
	}
}

/* Exercise 5 */ 
/* Action that writes a student's ID, name and grades in both numeric and letter format. */
void writeStudentData (tStudent student, float markCaa, float markPr, int nCaa, int nPr) {
	bool allSubmitted;

	allSubmitted = checkAllPRSubmitted(nPr);
	/* Data output */
	printf("%d ", student.studentId);
	printf("%s ", student.name);
	printf("%.2f ", markCaa);
	printf("%.2f ", markPr);
	printf("%d ", nCaa);
	printf("%d ", nPr);
	printf("%d\n", allSubmitted);
}

/* Exercise 6 */ 
/* Function that test all PR activities are submitted */
bool checkAllPRSubmitted(int nPr){
	int	expectednPr = 3;
	bool allSubmitted = false;

	if (nPr == expectednPr){
		allSubmitted = true;
	}
	return allSubmitted;
}
