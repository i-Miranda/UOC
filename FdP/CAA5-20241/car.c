#include "car.h"

/* Receives user input and saves it to tCar double pointer */
void setCar(tCar *car){
	printf("MODEL?\n");
	scanf("%s", car->name);
	printf("ENGINE (0-DIESEL, 1-GASOLINE, 2-ELECTRIC)?\n");
	scanf("%d", &car->engine);
	printf("CONSUMPTION PER 100KM?\n");
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
	if (car1->electricConsumption < car2->electricConsumption){
		*winner = *car1;
	} else {
		*winner = *car2;
	}
}

/* Outputs the car with winning stats */
void displayWinnerStats(tCar *car){
	printf("OUTPUT\n");
	printf("THE MOST EFFCIENT CAR IS:\n");
	printf("MODEL: %s\n", car->name);
	printf("ENGINE (0-DIESEL, 1-GASOLINE, 2-ELECTRIC): %d\n", car->engine);
	printf("CONSUMTION PER 100KM: %.2f\n", car->consumption);
	if (car->engine != ELECTRIC){	
		printf("ELECTRICAL EQUIVALENT CONSUMPTION: %.2f\n", car->electricConsumption);
	}
}
