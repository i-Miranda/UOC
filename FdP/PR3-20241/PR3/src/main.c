/*
* File: main.c
* Author: Ivan Miranda Moral
* Course: 20241
* Description: PR2
*/

/* System header files */
#include "grades.h"

/*Constant for file reading */
#define MAX_FILE_NAME 100+1


/* main */
int main(int argc, char **argv)
{
	tStudentsTable studentsTable;
	int i;
	char filename[MAX_FILE_NAME];
	bool isRead = false;
	
	/* load data from file*/ 
    printf("LOAD DATA FROM FILE. ENTER FILE NAME >>\n");
    scanf("%s", filename);

    /* Exercise 1 */	
    /* ... */	
	
	if (isRead) { /* Data successfully loaded */

           printf ("\nRESULTS:\n");
           printf("ID NAME PEC_MARK PR_MARK N_PEC N_PR ALL_PR?(1=YES)\n");
           
           for(i = 0; /* ... */; i++) {
               /* Exercise 2 */          
               /* ... */
               /* Exercise 4 */
               /* ... */
               /* Exercise 5 */
               /* ... */
           }
		
	}
	else {
		/* No data recovered */
		printf("NO STUDENTS RECOVERED\n");	
	}
		
	return 0;
}







