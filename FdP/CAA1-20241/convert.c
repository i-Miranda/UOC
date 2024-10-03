/*
 * Author: Ivan Miranda Moral
 * Date: 03-10-2024
 * Description: This program takes a Fahrenheit temperature value input
 * and converts and outputs it to a Kelvin temperature value.
 */

/* System header files */
#include <stdio.h>

/* Symbolic constants */
#define F_FREEZE_POINT 32
/* 5.0/9.0 equals 0.555556 */
#define FIVE_NINTHS 0.555556 
#define K_FREEZE_POINT 273.15

/* Predeclaration of actions and functions */
float requestInput(void);
float convertFToC(float fahrenheit);
float convertCToK(float celsius);
float convertFToK(float fahrenheit);
void printK(float kelvin);

/* Main function */
int main(int argc, char **argv){
	float fahrenheit;
	float kelvin;

	fahrenheit = requestInput();
	kelvin = convertFToK(fahrenheit);
	printK(kelvin);
	return 0;
}

/* Implementation of actions and functions */
float requestInput(void){
	float fahrenheit;
	printf("INPUT\nFAHRENHEIT DEGREES?\n");
	scanf("%2f", &fahrenheit);
	return fahrenheit;
}

float convertFToC(float fahrenheit){
	float celsius;

	celsius = (fahrenheit - F_FREEZE_POINT) * FIVE_NINTHS;
	return celsius;
}

float convertCToK(float celsius){
	float kelvin;

	kelvin = celsius + K_FREEZE_POINT;
	return kelvin;
}

float convertFToK(float fahrenheit){
	float conversion;
	
	conversion = convertFToC(fahrenheit);
	conversion = convertCToK(conversion);
	return conversion;
}

void	printK(float kelvin){
	printf("OUTPUT\nKELVIN: %.2f\n", kelvin);
}
