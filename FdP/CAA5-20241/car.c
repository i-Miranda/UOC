/*
 * Author: Ivan Miranda Moral
 * Date: 02-12-2024
 * Description: This program reads user keyboard input, and returns the most
 * efficient car according to the specs provided in the test.data file.
 */

#include "car.h"

/* Receives user input and saves it to tCar double pointer */
void setCar(tCar *car){
	printf("MODEL?\n");
	scanf("%s", car->name);
	printf("ENGINE (0-DIESEL, 1-GASOLINE, 2-ELECTRIC)?\n");
	scanf("%u", &car->engine);
	printf("CONSUMPTION PER 100 KM?\n");
	scanf("%f", &car->consumption);
}

/* Sets the tCar's electricConsumption using its engine to determine the value */
void setElectricConsumption(tCar *car){
	if (car->engine == DIESEL){
		car->electricConsumption = car->consumption * DIESEL_KWH;
	} else { 
		if (car->engine == GASOLINE){
			car->electricConsumption = car->consumption * GASOLINE_KWH;
		} else {
			car->electricConsumption = car->consumption;
		}
	}
}
/* Compares the cars' KWH to get the winner */
void getWinnerCar(tCar *car1, tCar *car2, tCar *winner){
	/* If car1's electricConsumption is less than car2, car1 wins */
	if (car1->electricConsumption < car2->electricConsumption){
		*winner = *car1;
	/* If car2's electricConsumption is less than car1, car2 wins */
	/* ---------------------------OR----------------------------- */
	/* ---------------If they tie, car2 still wins--------------- */
	} else {
		*winner = *car2;
	}
}

/* Outputs the car with winning stats */
void displayWinnerStats(tCar *car){
	printf("OUTPUT\n");
	printf("THE MOST EFFICIENT CAR IS:\n");
	printf("MODEL: %s\n", car->name);
	printf("ENGINE (0-DIESEL, 1-GASOLINE, 2-ELECTRIC): %u\n", car->engine);
	printf("CONSUMTION PER 100 KM: %.2f\n", car->consumption);
	printf("ELECTRICAL EQUIVALENT CONSUMPTION: %.2f\n", car->electricConsumption);
}
