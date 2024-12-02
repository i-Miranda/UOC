/* System header files */
#include <stdio.h>
#include <string.h>

/* Symbolic constants */
/* The max length of the car name string */
/* (THE MAX_LEN IS 25 PLUS THE NULL TERMINATOR...) */
#define MAX_LEN 25+1

typedef enum {GASOLINE, DIESEL, ELECTRIC} tEngine;

/* The tCar struct*/
typedef struct {
	char	name[MAX_LEN]; 			// The car's model name
	tEngine	engine;					// It's engine type
	float	consumption;			// It's consumption
									
	// If not electric, it's equivalent electrical consumption
	float	electricConsumption;	
} tCar;

/* Receives user input and saves it to tCar double pointer */
void setCar(tCar *car);
/* The getKWH prototype */
void getKWH(tCar *car);
/* Compares the cars to get the winner */
void getWinnerCar(tCar *car1, tCar *car2, tCar *winner);
/* Outputs the car with winning stats */
void displayWinnerStats(tCar *car);


