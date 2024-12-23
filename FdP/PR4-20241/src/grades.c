/*
* File: main.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR4
*/

#include "grades.h"

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
		studentsTable->nStudents = 0;
		while (!feof(fin) && studentsTable->nStudents < MAX_STUDENTS) {
			/* Remove any content from the line */
			line[0] = '\0';
			
			/* Read one line (maximum 511 chars) and store it in "line" variable */
			fgets (line,MAX_LINE - 1, fin);
			
			/* Ensure that the string is ended by 0*/
			line[MAX_LINE - 1] = '\0';
			
			if (strlen(line) > 0) {
				/* Obtain the data from file line*/
				sscanf(line, "%d %s %[^\n]s", &newStudent.studentId, newStudent.name, buffer);
	
				/* Read the grades and add them to the table */
				for (i = 0; i < NUM_ACTIVITIES; i++) {
                    sscanf(buffer, "%f %u %[^\n]s", &newStudent.activities[i].mark, &newStudent.activities[i].state, buffer);
                    if (i == 0) {
                        newStudent.activities[i].name = CAA1;
                    } else {
                        if (i == 1) {
                            newStudent.activities[i].name = CAA2;
                        } else {
                            if (i == 2) {
                                newStudent.activities[i].name = CAA3;
                            } else {
                                if (i == 3) {
                                    newStudent.activities[i].name = CAA4;
                                } else {
                                    if (i == 4) {
                                        newStudent.activities[i].name = PR1;
                                    } else {
                                        if (i == 5) {
                                            newStudent.activities[i].name = PR2;
                                        } else {
                                            newStudent.activities[i].name = PR3;                                                  
                                        }                                
                                    }
                                }
                            }
                        }
                    }
				}	
				
				/* Add student to students vector*/
				studentsTable->students[studentsTable->nStudents] = newStudent;
                /* Increment the counter. */
				studentsTable->nStudents++;						
			}
		}
		/* Close the file */
		fclose(fin);
		*isRead = true;
	} else {
		*isRead = false;
	}
}

/* Action that returns the type of activity and its weight */
void activityTypeWeight (tActivityName activity, tActivityType *activityType, int *activityWeight) {
    *activityType = CAA;
    if (activity == CAA1) {
        *activityWeight = CAA1_WEIGHT;
    } else {
        if (activity == CAA2) {
            *activityWeight = CAA2_WEIGHT;
        } else {
            if (activity == CAA3) {
                *activityWeight = CAA3_WEIGHT;
            } else {
                if (activity == CAA4) {
                    *activityWeight = CAA4_WEIGHT;
                } else {
                    *activityType = PR;
                    if (activity == PR1) {
                        *activityWeight = PR1_WEIGHT;
                    } else {
                        if (activity == PR2) {
                            *activityWeight = PR2_WEIGHT;
                        } else {
                            *activityWeight = PR3_WEIGHT;                                                  
                        }                                
                    }
                }
            }
        }
    }    
}

/* Function that test all PR activies are submited */
bool allSubmittedPr(int nSubmittedPr) {
    return nSubmittedPr == NUM_PR_ACTIVITIES;
}

/* Exercise 2 */
/* Action to calculate a student's CAA and PR marks and number of activities */
void	calculateStudentCaaAndPr(tStudent *student){
	tActivityType actType;
	int actWeight, i;

	/* Initialization before loop */
	i = 0;
	student->caaMark = 0.0f;
	student->prMark = 0.0f;
	student->nCaa = 0;
	student->nPr = 0;

	while (i < NUM_ACTIVITIES){
		activityTypeWeight(student->activities[i].name, &actType, &actWeight);
		if (student->activities[i].state == SUBMITTED){
			if (actType == CAA){
				student->nCaa++;	
				student->caaMark += student->activities[i].mark * (float)actWeight/PERCENT;
			} else {
				student->nPr++;
				student->prMark += student->activities[i].mark * (float)actWeight/PERCENT;
			}
		}
		i++;
	}
}

/* Exercise 3 */
/* Action that calculates student's final mark and NP registry */
void	calculateFinalMarkAndAbsent(tStudent *student){
	if (student->caaMark < MIN_C){
		student->finalMark = student->caaMark;
	} else {
		if (student->prMark < MIN_C_PLUS){
			student->finalMark = student->prMark;
		} else {
			student->finalMark = student->caaMark * (float)FINAL_CAA_WEIGHT/PERCENT; 
			student->finalMark += student->prMark * (float)FINAL_PR_WEIGHT/PERCENT; 
		}
	}
	if (student->finalMark >= MIN_C_PLUS && student->nPr < NUM_PR_ACTIVITIES){
		student->finalMark = MIN_C;	
	}
	if (student->nCaa < MIN_NUM_CAA_ACTIVITIES && student->nPr < MIN_NUM_PR_ACTIVITIES){
		student->absent = true;
	}
}

/* Exercise 4 */
/* Action that writes a student's ID, grade and NP in a file */
void	writeStudentsTableToFile(tStudentsTable *studentsTable, const char *filename){
	int	i;
	FILE* fileToWrite;

	i = 0;
	fileToWrite = fopen(filename, "w");
	while(i < studentsTable->nStudents){
		fprintf(fileToWrite, "%d ", studentsTable->students[i].studentId);
		fprintf(fileToWrite, "%s ", studentsTable->students[i].name);
		fprintf(fileToWrite, "%.2f ", studentsTable->students[i].caaMark);
		fprintf(fileToWrite, "%.2f ", studentsTable->students[i].prMark);
		fprintf(fileToWrite, "%d ", studentsTable->students[i].nCaa);
		fprintf(fileToWrite, "%d ", studentsTable->students[i].nPr);
		fprintf(fileToWrite, "%.2f ", studentsTable->students[i].finalMark);
		fprintf(fileToWrite, "%d\n", studentsTable->students[i].absent);
		i++;
	}
	fclose(fileToWrite);
}

/* Exercise 5 */ 
/* Action that order a student's table by mark and student id */	
void	sortStudentsTable(tStudentsTable *studentsTable){
	int i, j;
	tStudent tempStudent;
	
	i = studentsTable->nStudents - 1;
	while (i > 0){
		j = 0;
		while (j < i){
			if ((studentsTable->students[j].finalMark < studentsTable->students[j + 1].finalMark) 
				|| ((studentsTable->students[j].finalMark == studentsTable->students[j + 1].finalMark)
					&& (studentsTable->students[j].studentId > studentsTable->students[j + 1].studentId))){
				tempStudent = studentsTable->students[j];	
				studentsTable->students[j] = studentsTable->students[j + 1];
				studentsTable->students[j + 1] = tempStudent;
			}
			j++;
		}
		i--;
	}
}

/* Exercise 6 */ 
/* Function that calculates percent of students who have passed */
float	getPassingPercentage(tStudentsTable studentsTable){
	float passedPercent;
	int i, totalPassed;

	i = 0;
	totalPassed = 0;

	while (i < studentsTable.nStudents){
		if (studentsTable.students[i].finalMark >= MIN_C_PLUS){
			totalPassed++;	
		}
		i++;
	}

	passedPercent = (float)totalPassed/(float)studentsTable.nStudents * PERCENT;

	return passedPercent;
}

/* Exercise 7 */
/* Action that obtains students candidates to honors */
void	getHonorsCandidates(tStudentsTable *studentsTable, tStudentsTable *honorsTable){
	int	i = 0;

	while (i < studentsTable->nStudents){
		/* Minimum finalMark for Honors is 9.0 */
		if (studentsTable->students[i].finalMark >= MIN_A){
			/* Add student to honors table */
			honorsTable->students[honorsTable->nStudents] = studentsTable->students[i];
			/* Increment the counter. */
			honorsTable->nStudents++;						
		}	
		i++;
	}	
}

/* Exercise 8 */
/* Function that tests if a student is in honors table */
bool studentIsHonors(int studentId, tStudentsTable *honorsTable){
	bool isHonors = false;
	int	i = 0;

	while (i < honorsTable->nStudents){
		if (honorsTable->students[i].studentId == studentId){
			isHonors = true;
		}
		i++;
	}
	return isHonors; 
}

/* Action that writes a student's ID, grade and NP registry */
void writeStudentsData(tStudentsTable studentsTable) {
#ifdef TYPEDEF_COMPLETED
	int i;
    
	/* Data output */
    if (studentsTable.nStudents == 0) {
        printf("STUDENT LIST EMPTY\n");
    } else {
    printf("ID NAME CAA_MARK PR_MARK N_CAA N_PR FINAL_MARK ABSENT?(1=YES)\n");
    printf("-------------------------------------------------------------\n");

        for (i = 0 ; i < studentsTable.nStudents ; i++) {
            printf("%d %s %.2f %.2f %d %d %.2f %d\n", studentsTable.students[i].studentId, studentsTable.students[i].name, 
                    studentsTable.students[i].caaMark, studentsTable.students[i].prMark,
                    studentsTable.students[i].nCaa, studentsTable.students[i].nPr,
                    studentsTable.students[i].finalMark, studentsTable.students[i].absent);                                       
        }
    }	
#endif
}
