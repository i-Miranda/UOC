/*
 * Author: Ivan Miranda Moral
 * Date: 02-12-2024
 * Description: This program reads user keyboard input, and returns the most
 * efficient car according to the specs provided in the test.data file.
 */

/* Car header file */
#include <car.h>

/* Main function */
int main(int argc, char **argv){
	tCar	car[2];				// We are comparing TWO cars
	tCar	*winner;			// Where we store the winning car
	
	/* Get the car data */
	setCar(&car);

	displayWinnerStats(winner);
	return 0;
}

/* Receives user input and saves it to tCar double pointer */
void setCar(tCar **car){
	int i;

	printf("INPUT\n");
	i = 0;
	while (i <= 2){
		printf("CAR %d\n", i + 1);
		printf("MODEL?\n");
		scanf("%s", car[i]->name);
		printf("ENGINE (0-DIESEL, 1-GASOLINE, 2-ELECTRIC)?\n");
		scanf("%d", car[i]->engine);
		printf("CONSUMPTION PER 100KM?\n");
		scanf("%f", car[i]->consumption);
		i++;
	}
}

/* Compares the cars to get the winner */
void getWinnerCar(tCar *car1, tCar *car2, tCar *winner);

/* Outputs the car with winning stats */
void displayWinnerStats(tCar *car){
	printf("OUTPUT\n");
	printf("THE MOST EFFCIENT CAR IS:\n");
	printf("MODEL: %s\n", car->name);
	printf("ENGINE (0-DIESEL, 1-GASOLINE, 2-ELECTRIC): %d\n", car->engine);
	printf("CONSUMPTION PER 100KM: %.1f\n", car->consumption);
	if (car->engine != ELECTRIC){	
		printf("ELECTRICAL EQUIVALENT CONSUMPTION: %.2f\n", car->electricConsumption);
	}
}
