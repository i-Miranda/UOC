/*
 * Author: Ivan Miranda Moral
 * Date: 04-11-2024
 * Description: This program takes a student's grade number data
 * and converts and outputs it to letter grades.
 */

/* System header files */
#include <stdio.h>
/* Symbolic constants */
#define NUM_ACTIVITIES 5 	/* The number of activities used to calculate the student's average */
#define MIN_D 0.0 			/* Minimum number grade acquireable (NO NEGATIVE NUMBERS) */
#define MIN_C_MINUS 3.0 	/* Minimum number grade to acquire a C- */
#define MIN_C_PLUS 5.0 		/* Minimum number grade to acquire a C+ */
#define MIN_B 7.0 			/* Minimum number grade to acquire a B */
#define MIN_A 9.0 			/* Minimum number grade to acquire a A */
#define MAX_A 10.0 			/* Maximum number grade to acquire a A */

/* An enum representing letter grades */
typedef enum {A, B, C_PLUS, C_MINUS, D} tGrades;

/* Main function */
int main(int argc, char **argv){
	/* Variable Definitions */
	float activityGrade[NUM_ACTIVITIES]; 	/* Activity grades */
	float failedGrades[NUM_ACTIVITIES]; 	/* Grades below C+ */
	int	failedGradesCount; 					/* Count of failed grades */
	int	i; 									/* Iteration Variable */
	tGrades finalGrade; 					/* Final grade based on average */

	float finalGPA; 						/* Final grade point average */
	float bestGrade;						/* Highest grade attained */
	float worstGrade;						/* Lowest grade attained */

	/* Data Input */
	/* Initializing the variables */	
	for(i = 0; i < NUM_ACTIVITIES; i++){
		activityGrade[i] = 0.0;
		failedGrades[i] = -1.0;
	}

	failedGradesCount = 0;
	i = 0;
	finalGrade = D;

	finalGPA = 0.0;
	bestGrade = 0.0;
	worstGrade = 10.0;

	/* Input the grades */
	for(i = 1; i <= NUM_ACTIVITIES; i++){
		printf("ACTIVITY ID #%d\nGRADE (A REAL)?\n", i);
		scanf("%f", &activityGrade[i - 1]);
	}

	/* Data processing */
	/* Calculate the sum of all grades */
	/* Store the highest and lowest grades */ 
	for(i = 0; i < NUM_ACTIVITIES; i++){
		if (activityGrade[i] > MAX_A){		/* Check if grade is above 10.0 */
			activityGrade[i] = MAX_A;		/* Set to 10.0 */
		}
		if (activityGrade[i] < MIN_D){		/* Check if grade is below 0.0 */
			activityGrade[i] = MIN_D;		/* Set to 0.0 */
		}
		finalGPA += activityGrade[i];
		if (bestGrade < activityGrade[i]){	/* Check grade is above bestGrade */
			bestGrade = activityGrade[i];	/* Set bestGrade */
		}
		if (worstGrade > activityGrade[i]){ /* Check grade is below worstGrade */
			worstGrade = activityGrade[i];	/* Set worstGrade */
		}
	}

	/* Calculate the final grade point average */
	finalGPA = finalGPA/(float)NUM_ACTIVITIES;	

	/* Determine the final grade based on the average */
	if (finalGPA >= MIN_C_MINUS){
		finalGrade = C_MINUS;
	}
	if (finalGPA >= MIN_C_PLUS){
		finalGrade = C_PLUS;
	}
	if (finalGPA >= MIN_B){
		finalGrade = B;
	}
	if (finalGPA >= MIN_A){
		finalGrade = A;
	}

	/* Identify activities with grades below C+ and update failed grades count*/
	for(i = 0; i < NUM_ACTIVITIES; i++){
		if (activityGrade[i] < MIN_C_PLUS){
			failedGrades[i] = activityGrade[i];			
			failedGradesCount++;
		}
	}

	/* Data output */
	printf("FINAL GRADE (1-A, 2-B, 3-C_PLUS, 4-C_MINUS, 5-D): %d\n", finalGrade + 1);
	printf("BEST GRADE: %.2f\n", bestGrade);
	printf("WORST GRADE: %.2f\n", worstGrade);

	/* Display grades below C+ */
	if (failedGradesCount > 0) {
		printf("GRADES BELOW C_PLUS:\n");
		for(i = 0; i < NUM_ACTIVITIES; i++){
			/* if failedGrades[i] >= 0.0 it's been recorded as a failed grade */
			if (failedGrades[i] >= MIN_D && failedGrades[i] < MIN_C_PLUS){
				printf("ACTIVITY ID: #%d\nACTIVITY GRADE: %.2f\n", i + 1, failedGrades[i]);
			}
		}
	} else {
		printf("NO GRADES BELOW C_PLUS.");
	}

	return 0;
}
