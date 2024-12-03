/*
 * Author: Ivan Miranda Moral
 * Date: 02-12-2024
 * Description: This program reads user keyboard input, and returns the most
 * efficient car according to the specs provided in the test.data file.
 */

/* Car header file */
#include "car.h"

/* Main function */
int main(int argc, char **argv){
	tCar	carFirst;			// The first car
	tCar	carSecond;			// The second car
	tCar	winner;			// Where we store the winning car
	
	printf("INPUT\n");
	printf("CAR 1\n");
	/* Get the first car data */
	setCar(&carFirst);
	printf("CAR 2\n");
	/* Get the second car data */
	setCar(&carSecond);
	/* Convert cars consumption to electric consumption */
	setElectricConsumption(&carFirst);
	setElectricConsumption(&carSecond);
	/* Compares both cars to get the winner */
	getWinnerCar(&carFirst, &carSecond, &winner);	
	/* Displays winner */
	displayWinnerStats(&winner);
	return 0;
}

