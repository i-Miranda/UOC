/*
 * Author: Ivan Miranda Moral
 * Date: 12-10-2024
 * Description: This program checks the availability of a museum's tickets
 * based on the number of visitors at a given time.
 */

/* System header files */
#include <stdio.h>

/* Symbolic constants */
/* Max visitor capacity in museum*/
#define MAX_VISITORS 220
/* Reserved for students and members on working days */
#define WORKING_DAYS 44 

/* Main function */
int main(int argc, char **argv){
	int visitors;
	int ticketType;
	int ticketsAvailable;
	int ticketCount;
	int isAvailable;
	int isHoliday;

	printf("INPUT\nVISITORS INSIDE?\n");
	scanf("%d", &visitors);
	printf("IS A HOLIDAY (0-FALSE, 1-TRUE)?\n");
	scanf("%d", &isHoliday);
	printf("TICKET TYPE (0-STUDENT, 1-MEMBER, 2-REGULAR)?\n");
	scanf("%d", &ticketType);
	ticketsAvailable = MAX_VISITORS - visitors - (WORKING_DAYS * (isHoliday == 0 && ticketType == 2));
	printf("NUMBERS OF TICKETS?\n");
	scanf("%d", &ticketCount);
	isAvailable = (ticketsAvailable >= ticketCount);
	printf("OUTPUT\nTICKETS AVAILABLE (0-FALSE, 1-TRUE): %d\n", isAvailable);
	return 0;
}
