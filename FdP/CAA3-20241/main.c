/*
 * Author: Ivan Miranda Moral
 * Date: 21-10-2024
 * Description: This program reads a test.data file, and returns the the type
 * of matrix stored inside.
 */

/* System header files */
#include <stdio.h>

/* Symbolic constants */
/* MATRIX types that are not UPPER TRIANGLE */
#define OTHER_MATRIX 0
/* UPPER TRIANGLE MATRIX type*/
#define UT_MATRIX 1 
/* STRICTLY UPPER TRIANGLE MATRIX type*/
#define STRICT_UT_MATRIX 2 

/* Main function */
int main(int argc, char **argv){
	FILE* testData;
	char matrixData[13];
	int	result;

	testData = fopen("test.data", "r");
	fscanf(testData, "%s", matrixData);
	printf("%s\n", matrixData);
	printf("OUTPUT\n");
	if (result == OTHER_MATRIX){
		printf("OTHER MATRIX TYPE\n");
	} else {
		if (result == UT_MATRIX){
			printf("UPPER TRIANGLE MATRIX\n");
		} else {
			printf("STRICTLY UPPER TRIANGLE MATRIX\n");
	}
	return fclose(testData);
}
