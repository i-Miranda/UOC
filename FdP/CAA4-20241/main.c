/*
 * Author: Ivan Miranda Moral
 * Date: 10-11-2024
 * Description: This program reads a test.data file, and returns the best
 * e-bike according to the specs provided in the test.data file.
 */

/* System header files */
#include <stdio.h>
#include <string.h>

/* Symbolic constants */
/* The max length of the bike name string */
/* (THE MAX_LEN IS 30 PLUS THE NULL TERMINATOR...) */
#define MAX_LEN 31

/* The tBike struct*/
typedef struct {
	char	name[MAX_LEN]; 		// The e-bike's name
	int		battery;			// It's battery capacity
	float	weight;				// It's weight
} tBike;

/* Main function */
int main(int argc, char **argv){
	FILE*	testData;			// The file
	tBike	bike[2];			// We are comparing TWO bikes
	int		i;					// Iterator for initializing bike name strings
	char	winner[MAX_LEN];	// Where we store the winning bike name
	
	/* Since fscanf DOES NOT null terminate strings, here we 		  */
	/* initialize the bike names and winner with null terminators 	  */
	/* to avoid issues while using both strcpy and strcmp later on... */
	i = 0;
	while (i < MAX_LEN){
		bike[0].name[i] = '\0';	
		bike[1].name[i] = '\0';
		winner[i] = '\0';
		i++;
	}

	/* Get the bike data */
	testData = fopen("test.data", "r");
	fscanf(testData, "%s %d %f", bike[0].name, &bike[0].battery, &bike[0].weight);
	fscanf(testData, "%s %d %f", bike[1].name, &bike[1].battery, &bike[1].weight);
	fclose(testData);

	/* Find the better bike  */
	/* Order of priority:	 */
	/* 1) battery capacity	 */
	/* 2) weight			 */
	/* 3) alphabetical order */
	/* if all else fails...  */
	/* first bike wins	     */
	if (bike[0].battery != bike[1].battery){
		if (bike[0].battery > bike[1].battery){
			strcpy(winner, bike[0].name);
		} else {
			strcpy(winner, bike[1].name);
		}
	} else {
		if (bike[0].weight != bike[1].weight){
			if (bike[0].weight < bike[1].weight){
				strcpy(winner, bike[0].name);
			} else {
				strcpy(winner, bike[1].name);
			}
		} else {
			/* strcmp compares strings char by char until reaching the null */
			/* null terminator, returning the difference between the first	*/
			/* two chars that are different. If the number is negative the	*/
			/* first argument is a lower char value.						*/
			if (strcmp(bike[0].name, bike[1].name) <= 0){
				strcpy(winner, bike[0].name);
			} else {
				strcpy(winner, bike[1].name);
			}
		}
	}
	
	/* Print the result */
	printf("OUTPUT\nTHE BEST E-BIKE IS %s\n", winner);
	return 0;
}
