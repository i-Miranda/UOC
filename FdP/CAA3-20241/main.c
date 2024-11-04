/*
 * Author: Ivan Miranda Moral
 * Date: 21-10-2024
 * Description: This program reads a test.data file, and returns the the type
 * of matrix stored inside.
 */

/* System header files */
#include <stdio.h>
#include <string.h>

/* Symbolic constants */
/* MATRIX dimensions */
#define MAX_ROWS 3
/* Max columns is 4 for Null-terminator*/
#define MAX_COLS 4
/* Null-terminated string of zeroes */
#define THREE_ZEROES "000\0"

/* MATRIX types */
typedef enum {STRICT, UPPER, OTHER} matrixType;

/* Main function */
int main(int argc, char **argv){
	FILE* testData;
	char matrixData[MAX_ROWS][MAX_COLS];
	char strictTri[MAX_COLS];
	char upperTri[MAX_COLS];
	matrixType matrix;

	testData = fopen("test.data", "r");
	matrix = STRICT;
	strcpy(matrixData[0], THREE_ZEROES);
	strcpy(matrixData[1], THREE_ZEROES);
	strcpy(matrixData[2], THREE_ZEROES);
	strcpy(strictTri, THREE_ZEROES);
	strcpy(upperTri, THREE_ZEROES);

	fscanf(testData, "%s", &matrixData[0][0]);
	fscanf(testData, "%s", &matrixData[0][1]);
	fscanf(testData, "%s", &matrixData[0][2]);
	fscanf(testData, "%s", &matrixData[1][0]);
	fscanf(testData, "%s", &matrixData[1][1]);
	fscanf(testData, "%s", &matrixData[1][2]);
	fscanf(testData, "%s", &matrixData[2][0]);
	fscanf(testData, "%s", &matrixData[2][1]);
	fscanf(testData, "%s", &matrixData[2][2]);
	
	strictTri[0] = matrixData[0][0];
	strictTri[1] = matrixData[1][1];
	strictTri[2] = matrixData[2][2];
	upperTri[0] = matrixData[1][0];
	upperTri[1] = matrixData[2][1];
	upperTri[2] = matrixData[2][0];

	if (strcmp(strictTri, THREE_ZEROES) != 0){ 
		matrix = UPPER;
	}
	if (strcmp(upperTri, THREE_ZEROES) != 0){
		matrix = OTHER;
	}

	printf("OUTPUT\n");
	if (matrix == STRICT){
		printf("STRICTLY UPPER TRIANGULAR MATRIX\n");
	} else {
		if (matrix == UPPER){
			printf("UPPER TRIANGULAR MATRIX\n");
		} else {
			printf("OTHER MATRIX TYPE\n");
		}
	}

	fclose(testData);
	return 0;
}
