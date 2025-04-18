/*
 * File: api.c
 * Author: Ivan Miranda Moral
 * Date: 05-04-2025
 * Description: Available methods and exercises for PR1
 */

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "csv.h"
#include "api.h"
#include <string.h>

// Get the API version information
const char* api_version()
{
    return "UOC PP 20242";
}

// Load data from a CSV file. If reset is true, remove previous data
tApiError api_loadData(tApiData* data, const char* filename, bool reset)
{
    tApiError error;
    FILE *fin;    
    char buffer[FILE_READ_BUFFER_SIZE];
    tCSVEntry entry;
    
    // Check input data
    assert( data != NULL );
    assert(filename != NULL);
    
    // Reset current data    
    if (reset) {
        // Remove previous information
        error = api_freeData(data);
        if (error != E_SUCCESS) {
            return error;
        }
        
        // Initialize the data
        error = api_initData(data);
        if (error != E_SUCCESS) {
            return error;
        }
    }

    // Open the input file
    fin = fopen(filename, "r");
    if (fin == NULL) {
        return E_FILE_NOT_FOUND;
    }
    
    // Read file line by line
    while (fgets(buffer, FILE_READ_BUFFER_SIZE, fin)) {
        // Remove new line character     
        buffer[strcspn(buffer, "\n\r")] = '\0';
        
        csv_initEntry(&entry);
        csv_parseEntry(&entry, buffer, NULL);
        // Add this new entry to the api Data
        error = api_addDataEntry(data, entry);
        if (error != E_SUCCESS) {
			csv_freeEntry(&entry);
			fclose(fin);
            return error;
        }
        csv_freeEntry(&entry);

    }
    
    fclose(fin);
    
    return E_SUCCESS;
}

// Initialize the data structure
tApiError api_initData(tApiData* data) {
	/////////////////////////////////
	// PR1_3b
	/////////////////////////////////
	tApiError apiError;

	assert(data != NULL);	

	//putting mallocs inside stack allocated data is asking for trouble
	apiError = people_init(&data->people);
	apiError = subscriptions_init(&data->subscriptions);
	apiError = catalog_init(&data->catalog);
    /////////////////////////////////
    return apiError;
}

// Add a person into the data if it does not exist
tApiError api_addPerson(tApiData* data, tCSVEntry entry) {
	/////////////////////////////////
	// PR1_3c
	/////////////////////////////////
	tApiError	apiError;
	tPerson		person;

	assert(data != NULL && &entry != NULL);	
	if (csv_numFields(entry) != NUM_FIELDS_PERSON) {
		return E_INVALID_ENTRY_FORMAT;
	}
	if (strcmp(csv_getType(&entry), "PERSON") != 0) {
		return E_INVALID_ENTRY_TYPE;
	}
	person_parse(&person, entry);
	apiError = people_add(&data->people, person);
	person_free(&person);
	/////////////////////////////////
    return apiError;
}

// Add a subscription if it does not exist
tApiError api_addSubscription(tApiData* data, tCSVEntry entry) {
	/////////////////////////////////
	// PR1_3d
	/////////////////////////////////
	tApiError		apiError;
	tSubscription	subscription;

	assert(data != NULL && &entry != NULL);	
	if (csv_numFields(entry) != NUM_FIELDS_SUBSCRIPTION) {
		return E_INVALID_ENTRY_FORMAT;
	}
	if (strcmp(csv_getType(&entry), "SUBSCRIPTION") != 0) {
		return E_INVALID_ENTRY_TYPE;
	}
	subscription_parse(&subscription, entry);
	apiError = subscriptions_add(&data->subscriptions, data->people, subscription);
	/////////////////////////////////
    return apiError;
}

// Add a film if it does not exist
tApiError api_addFilm(tApiData* data, tCSVEntry entry) {
	/////////////////////////////////
	// PR1_3e
	/////////////////////////////////
	tApiError	apiError;
	tFilm		film;

	assert(data != NULL && &entry != NULL);	
	if (csv_numFields(entry) != NUM_FIELDS_FILM) {
		return E_INVALID_ENTRY_FORMAT;
	}
	if (strcmp(csv_getType(&entry), "FILM") != 0) {
		return E_INVALID_ENTRY_TYPE;
	}
	film_parse(&film, entry);
	apiError = catalog_add(&data->catalog, film);
	film_free(&film);
	/////////////////////////////////
    return apiError;
}

// Get the number of people registered on the application
int api_peopleCount(tApiData data) {
	/////////////////////////////////
	// PR1_3f
	/////////////////////////////////
	int	count;

	assert(&data != NULL);

	count = people_count(data.people);	
	return count;
	/////////////////////////////////
}

// Get the number of subscriptions registered on the application
int api_subscriptionsCount(tApiData data) {
	/////////////////////////////////
	// PR1_3f
	/////////////////////////////////
	int	count;

	assert(&data != NULL);

	count = subscriptions_len(data.subscriptions);
	return count;	
	/////////////////////////////////
}

// Get the number of films registered on the application
int api_filmsCount(tApiData data) {
	/////////////////////////////////
	// PR1_3f
	/////////////////////////////////
	int count;

	assert(&data != NULL);

	count = catalog_len(data.catalog);
	return count;
	/////////////////////////////////
}

// Get the number of free films registered on the application
int api_freeFilmsCount(tApiData data) {
	/////////////////////////////////
	// PR1_3f
	/////////////////////////////////
	int	count;

	assert(&data != NULL);

	count = catalog_freeLen(data.catalog);
	return count;
	/////////////////////////////////
}

// Free all used memory
tApiError api_freeData(tApiData* data) {
	/////////////////////////////////
	// PR1_3g
	/////////////////////////////////
	assert(data != NULL);	

	people_free(&data->people);
	subscriptions_free(&data->subscriptions);
	catalog_free(&data->catalog);
    /////////////////////////////////
    return E_SUCCESS;
}

// Add a new entry
tApiError api_addDataEntry(tApiData* data, tCSVEntry entry) {
	/////////////////////////////////
	// PR1_3h
	/////////////////////////////////
	int				numFields;
	tApiError		apiError;

	assert(data != NULL && &entry != NULL);
	// numFields is used to verify which data type 
	numFields = csv_numFields(entry);	
	if (numFields == NUM_FIELDS_PERSON) {
		apiError = api_addPerson(data, entry); 
	} else {
		if (numFields == NUM_FIELDS_SUBSCRIPTION) {
			apiError = api_addSubscription(data, entry);
		} else {
			if (numFields == NUM_FIELDS_FILM) {
				apiError = api_addFilm(data, entry);
			} else {
				apiError = E_INVALID_ENTRY_FORMAT;
			}
		}
	}
	return apiError;
    /////////////////////////////////
}

// Get subscription data
tApiError api_getSubscription(tApiData data, int id, tCSVEntry *entry) {
	/////////////////////////////////
	// PR1_4a
	/////////////////////////////////
	char		buffer[2048];
	tApiError	apiError;
	int			subscriptionIndex;

	assert(&data != NULL && entry != NULL);	
	// First get the subscriptionIndex with subscriptions_find
	subscriptionIndex = subscriptions_find(data.subscriptions, id);
	if (subscriptionIndex != -1) {
		// Then use the subscriptionIndex to get the buffer for the entry
		subscriptions_get(data.subscriptions, subscriptionIndex, buffer);
		csv_parseEntry(entry, buffer, "SUBSCRIPTION");	
		return E_SUCCESS;
	}
	/////////////////////////////////
    return E_SUBSCRIPTION_NOT_FOUND;
}

// Get film data
tApiError api_getFilm(tApiData data, const char* name, tCSVEntry *entry) {
	/////////////////////////////////
	// PR1_4b
	/////////////////////////////////
	char		buffer[2048];
	tFilm*		film;

	assert(&data != NULL && entry != NULL);	
	film = filmList_find(data.catalog.filmList, name);
	if (film != NULL) {
		film_get(*film, buffer);
		csv_parseEntry(entry, buffer, "FILM");	
		return E_SUCCESS;
	}
	/////////////////////////////////
	return E_FILM_NOT_FOUND;
}

// Get free films data
tApiError api_getFreeFilms(tApiData data, tCSVData *freeFilms) {
	/////////////////////////////////
	// PR1_4c
	/////////////////////////////////
	char		buffer[2048];
	tFilm*		freeFilm;

	assert(&data != NULL && freeFilms != NULL);	
	// Iterate along the freeFilmList to add all of the data to freeFilms
	while(data.catalog.freeFilmList.first != NULL) {
		freeFilm = data.catalog.freeFilmList.first->elem;
		if (freeFilm != NULL) {
			film_get(*freeFilm, buffer);
			csv_addStrEntry(freeFilms, buffer, "FILM");	
		}
		data.catalog.freeFilmList.first = data.catalog.freeFilmList.first->next;
	}
	/////////////////////////////////
	return E_SUCCESS;
}

// Get films data by genre
tApiError api_getFilmsByGenre(tApiData data, tCSVData *films, int genre) {
	/////////////////////////////////
	// PR1_4d
	/////////////////////////////////
	char		buffer[2048];
	tFilm*		film;

	assert(&data != NULL && films != NULL);	
	// Iterate along the filmList to add all of the data to films
	while(data.catalog.filmList.first != NULL) {
		film = &data.catalog.filmList.first->elem;
		if (film != NULL && film->genre == genre) {
			film_get(*film, buffer);
			csv_addStrEntry(films, buffer, "FILM");	
		}
		data.catalog.filmList.first = data.catalog.filmList.first->next;
	}
	/////////////////////////////////
    return E_SUCCESS;
}
