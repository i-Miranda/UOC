/*
 * File: watchstack.c
 * Author: Ivan Miranda Moral
 * Date: 02-05-2025
 * Description:  watchstack.c file for exercises for PR2
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdbool.h>
#include "film.h"
#include "watchstack.h"

// Initializes the stack
tApiError filmstack_init(tFilmstack* stack) {
    /////////////////////////////////
    // PR2_2c
    /////////////////////////////////
	assert(stack != NULL);	

  	stack->top = NULL;	 
	stack->count = 0;
    return E_SUCCESS;
}

// Returns true if the stack is empty
bool filmstack_isEmpty(tFilmstack stack) {
    /////////////////////////////////
    // PR2_2d
    /////////////////////////////////
	assert(&stack != NULL);

	if (stack.count == 0) {
		return true;
	}
    return false;
}

// Adds a new film to the stack, unless it already exists
tApiError filmstack_push(tFilmstack* stack, tFilm film) {
    /////////////////////////////////
    // PR2_2e
    /////////////////////////////////
	tFilmstackNode* newNode;
	tFilmstackNode* tempNode;

	assert(stack != NULL);
	assert(&film != NULL);

	tempNode = stack->top;
	while (tempNode != NULL) {
		if (film_equals(tempNode->elem, film)) {
			return E_FILM_ALREADY_EXISTS;
		}
		tempNode = tempNode->next;
	}
	newNode = malloc(1 * sizeof(tFilmstackNode));
	if (newNode == NULL) {
		return E_MEMORY_ERROR;
	}
	film_cpy(&newNode->elem, film);
	newNode->next = stack->top;
	stack->top = newNode;
	stack->count++;
    return E_SUCCESS;
}

// Returns the top film (without removing it)
tFilm* filmstack_top(tFilmstack stack) {
    /////////////////////////////////
    // PR2_2f
    /////////////////////////////////
	tFilm* film;

	assert(&stack != NULL);
  	 
	if (filmstack_isEmpty(stack) == true) {
    	return NULL;
	}
	return &stack.top->elem;
}

// Removes the top film from the stack
tApiError filmstack_pop(tFilmstack* stack) {
    /////////////////////////////////
    // PR2_2g
    /////////////////////////////////
	tFilmstackNode* tmp;

	assert(stack != NULL);

	if (filmstack_isEmpty(*stack) == true) {
		return E_STRUCTURE_EMPTY;
	}
	tmp = stack->top->next;	
	free(stack->top->elem.name);
	free(&stack->top->elem);
	stack->top = tmp;
	stack->count--;
    return E_SUCCESS;
}

// Frees the whole stack
void filmstack_free(tFilmstack* stack) {
    /////////////////////////////////
    // PR2_2f
    /////////////////////////////////

	assert(stack != NULL);
    
	while (filmstack_isEmpty(*stack) == false) {
		filmstack_pop(stack);
	}
	stack = NULL;
}
