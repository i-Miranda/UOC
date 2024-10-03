/*
 * Author: Ivan Miranda Moral
 * Date: 03-10-2024
 * Description: This program takes a Fahrenheit temperature value input
 * and converts and outputs it to a Kelvin temperature value.
 */

/* System header files */
#include <stdio.h>

/* Symbolic constants */
/* Fahrenheit freezing point of water */
#define F_FREEZE_POINT 32
/* 5.0/9.0 equals 0.555556 */
#define FIVE_NINTHS 0.555556
/* Kelvin freezing point of water */
#define K_FREEZE_POINT 273.15

/* Main function */
int main(int argc, char **argv){
	float fahrenheit;
	float celsius;
	float kelvin;

	printf("INPUT\nFAHRENHEIT DEGREES?\n");
	scanf("%2f", &fahrenheit);
	celsius = (fahrenheit - F_FREEZE_POINT) * FIVE_NINTHS;
	kelvin = celsius + K_FREEZE_POINT;
	printf("OUTPUT\nKELVIN: %.2f\n", kelvin);
	return 0;
}
