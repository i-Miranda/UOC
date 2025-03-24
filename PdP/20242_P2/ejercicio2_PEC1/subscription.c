/*
 * File: subscription.c
 * Author: Ivan Miranda Moral
 * Date: 09-03-2025
 * Description: Available methods and exercises for PEC1
 */

#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <stdio.h>
#include "subscription.h"

//////////////////////////////////
// Available methods
//////////////////////////////////

// Parse a tDate from string information
void date_parse(tDate* date, const char* s_date) {
    // Check output data
    assert(date != NULL);

    // Check input date
    assert(s_date != NULL);
    assert(strlen(s_date) == 10);

    // Parse the input date
    sscanf(s_date, "%d/%d/%d", &(date->day), &(date->month), &(date->year));
}

// Copy the data from the source to destination
void date_cpy(tDate* destination, tDate source) {
    destination->day = source.day;
    destination->month = source.month;
    destination->year = source.year;
}

// Copy the data from the source to destination
void person_cpy(tPerson* destination, tPerson source) {
    // Copy identity document data
    strncpy(destination->document, source.document, MAX_DOCUMENT + 1);

    // Copy name data
    strncpy(destination->name, source.name, MAX_CHARS + 1);

    // Copy surname data
    strncpy(destination->surname, source.surname, MAX_CHARS + 1);

    // Copy phone data
    strncpy(destination->phone, source.phone, MAX_PHONE + 1);

    // Copy email data
    strncpy(destination->email, source.email, MAX_CHARS + 1);

    // Copy address data
    strncpy(destination->address, source.address, MAX_CHARS + 1);

    // Copy cp data
    strncpy(destination->cp, source.cp, MAX_CP + 1);

    // Copy the birthday date
    date_cpy(&(destination->birthday), source.birthday);
}

// Get person data using a string
void person_get(tPerson data, char* buffer)
{
    // Print all data at same time
    sprintf(buffer,"%s;%s;%s;%s;%s;%s;%s;%02d/%02d/%04d",
        data.document,
        data.name,
        data.surname,
        data.phone,
        data.email,
        data.address,
        data.cp,
        data.birthday.day, data.birthday.month, data.birthday.year);
}

// Initialize the people data
void people_init(tPeople* data) {
    // Check input/output data
    assert(data != NULL);

    data->count = 0;
}

// Get person data of position index using a string
void people_get(tPeople data, int index, char* buffer) {
		assert(index >= 0 && index < data.count);
		person_get(data.elems[index], buffer);
	}

// Return the position of a person with provided document. -1 if it does not exist
int people_find(tPeople data, const char* document) {
	int i = 0;
	while (i < data.count) {
		if (strcmp(data.elems[i].document, document) == 0) {
			return i;
		}
		i++;
	}

	return -1;
}

// Print people data
void people_print(tPeople data) {
	char buffer[1024];
	int i;
	for (i = 0; i < data.count; i++) {
		people_get(data, i, buffer);
		printf("%s\n", buffer);
	}
}

// Print subscriptions data
void subscriptions_print(tSubscriptions data) {
	char buffer[1024];
	int i;
	for (i = 0; i < data.count; i++) {
		subscriptions_get(data, i, buffer);
		printf("%s\n", buffer);
	}
}

//////////////////////////////////////
// EX2: Implement your methods here....
//////////////////////////////////////

// Parse input from CSVEntry
void person_parse(tPerson* data, tCSVEntry entry) {
	// Ex. 2a
	//Checking if input and output values are correct
	assert(data != NULL && &entry != NULL);
	assert(csv_numFields(entry) == 8 && csv_getType(&entry) != NULL);
	assert(entry.fields != NULL);

	//If the entry is a valid document length...
	assert(entry.fields[0] != NULL && strlen(entry.fields[0]) <= MAX_DOCUMENT);
	csv_getAsString(entry, 0, data->document, MAX_DOCUMENT + 1);

	//If the entry is a valid name length...
	assert(entry.fields[1] != NULL && strlen(entry.fields[1]) <= MAX_CHARS);
	csv_getAsString(entry, 1, data->name, MAX_CHARS + 1);

	//If the entry is a valid surname length...
	assert(entry.fields[2] != NULL && strlen(entry.fields[2]) <= MAX_CHARS);
	csv_getAsString(entry, 2, data->surname, MAX_CHARS + 1);

	//If the entry is a valid phone length...
	assert(entry.fields[3] != NULL && strlen(entry.fields[3]) <= MAX_PHONE);
	csv_getAsString(entry, 3, data->phone, MAX_PHONE + 1);

	//If the entry is a valid email length...
	assert(entry.fields[4] != NULL && strlen(entry.fields[4]) <= MAX_CHARS);
	csv_getAsString(entry, 4, data->email, MAX_CHARS + 1);

	//If the entry is a valid address length...
	assert(entry.fields[5] != NULL && strlen(entry.fields[5]) <= MAX_CHARS);
	csv_getAsString(entry, 5, data->address, MAX_CHARS + 1);

	//If the entry is a valid cp length...
	assert(entry.fields[6] != NULL && strlen(entry.fields[6]) <= MAX_CP);
	csv_getAsString(entry, 6, data->cp, MAX_CP + 1);

	//If the entry is a valid date length...
	assert(entry.fields[7] != NULL && strlen(entry.fields[7]) <= MAX_DATE);
	date_parse(&data->birthday, entry.fields[7]);
}

// Add a new person to people data
void people_add(tPeople* data, tPerson person) {
	// Ex. 2b
	//Checking if input and output values are correct
	assert(data != NULL && &person != NULL);

	//If the person does not exist in tPeople data...
	if (people_find(*data, person.document) == -1) {
		person_cpy(&data->elems[data->count], person);
		data->count++;
	}
}

// Parse input from CSVEntry
void subscription_parse(tSubscription* data, tCSVEntry entry) {
	// Ex. 2c
	//Checking if input and output values are correct
	assert(data != NULL && &entry != NULL);
	assert(csv_numFields(entry) == 7 && csv_getType(&entry) != NULL);
	assert(entry.fields != NULL);

	assert(entry.fields[0] >= 0);
	data->id = csv_getAsInteger(entry, 0);

	//If the entry is a valid document length...
	assert(entry.fields[1] != NULL && strlen(entry.fields[1]) <= MAX_DOCUMENT);
	csv_getAsString(entry, 1, data->document, MAX_DOCUMENT + 1);

	//If the entry is a valid date length...
	assert(entry.fields[2] != NULL && strlen(entry.fields[2]) <= MAX_DATE);
	date_parse(&data->startDate, entry.fields[2]);

	//If the entry is a valid date length...
	assert(entry.fields[3] != NULL && strlen(entry.fields[3]) <= MAX_DATE);
	date_parse(&data->endDate, entry.fields[3]);

	//If the entry is a valid plan length...
	assert(entry.fields[4] != NULL && strlen(entry.fields[4]) <= MAX_PLAN);
	csv_getAsString(entry, 4, data->plan, MAX_PLAN + 1);

	data->price = csv_getAsReal(entry, 5);
	if (data->price < 0.0f) {
		data->price = 0.0f;
	}

	data->numDevices = csv_getAsInteger(entry, 6);
	if (data->numDevices < 1) {
		data->numDevices = 1;
	}
}

// Copy the data from the source to destination (individual data)
void subscription_cpy(tSubscription* destination, tSubscription source) {
    // Ex. 2d
	//Checking if input and output values are correct
	assert(destination != NULL && &source != NULL);

	destination->id = source.id;
	strncpy(destination->document, source.document, MAX_DOCUMENT + 1);
	date_cpy(&destination->startDate, source.startDate);
	date_cpy(&destination->endDate, source.endDate);
	strncpy(destination->plan, source.plan, MAX_PLAN + 1);
	destination->price = source.price;
	destination->numDevices = source.numDevices;
}

// Get subscription data using a string
void subscription_get(tSubscription data, char* buffer) {
    // Ex. 2e
	//Checking if input and output values are correct
	assert(&data != NULL && buffer != NULL);

	sprintf(buffer,"%d;%s;%02d/%02d/%04d;%02d/%02d/%04d;%s;%g;%d",
		data.id,
		data.document,
		data.startDate.day, data.startDate.month, data.startDate.year,
		data.endDate.day, data.endDate.month, data.endDate.year,
		data.plan,
		data.price,
		data.numDevices);
}

// Initialize subscriptions data
void subscriptions_init(tSubscriptions* data) {
    // Ex. 2f
	//Checking if input and output values are correct
	assert(data != NULL);

	data->count = 0;
}

// Add a new subscription
void subscriptions_add(tSubscriptions* data, tPeople people, tSubscription subscription) {
    // Ex. 2g
	//Checking if input and output values are correct
	assert(data != NULL && &people != NULL && &subscription != NULL);

	//If the person does not exist in tPeople people and 
	//the subscription does not exist in tSubscriptions data...
	if (people_find(people, subscription.document) != -1 
			&& subscriptions_find(*data, subscription.id) == -1) {
		subscription_cpy(&data->elems[data->count], subscription);
		data->count++;
	}
}

// Get subscription data of position index using a string
void subscriptions_get(tSubscriptions data, int index, char* buffer) {
    // Ex. 2h
	//Checking if input and output values are correct
	assert(&data != NULL && data.elems != NULL && &data.elems[index] != NULL);
	assert(index >= 0 && buffer != NULL);

	subscription_get(data.elems[index], buffer);
}

// Returns the position of a subscription looking for id's subscription. -1 if it does not exist
int subscriptions_find(tSubscriptions data, int id) {
    // Ex. 2i
	int	i;	
	//Checking if input and output values are correct
	assert(&data != NULL && data.elems != NULL && id > 0);

	for (i = 0; i < data.count; i++) {
		assert(&data.elems[i] != NULL);
		if (data.elems[i].id == id)
			return i;
	}
    return -1;
}
