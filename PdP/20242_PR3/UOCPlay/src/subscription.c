/*
 * File: subscription.c
 * Author: Ivan Miranda Moral
 * Date: 30-05-2025
 * Description: subscription.c file for exercises for PR3
 */

#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <stdio.h>
#include "subscription.h"

// Parse input from CSVEntry
void subscription_parse(tSubscription* data, tCSVEntry entry) {
    // Check input data
    assert(data != NULL);

    // Check entry fields
    assert(csv_numFields(entry) == NUM_FIELDS_SUBSCRIPTION);

    int pos = 0; // Allow to easy incremental position of the income data

    // Copy subscription's id data
    data->id = csv_getAsInteger(entry, pos);

    // Copy identity document data
    assert(strlen(entry.fields[++pos]) == MAX_DOCUMENT);
    csv_getAsString(entry, pos, data->document, MAX_DOCUMENT + 1);

    // Parse start date
    assert(strlen(entry.fields[++pos]) == DATE_LENGTH);
    date_parse(&(data->start_date), entry.fields[pos]);

    // Parse end date
    assert(strlen(entry.fields[++pos]) == DATE_LENGTH);
    date_parse(&(data->end_date), entry.fields[pos]);

    // Copy plan data
    csv_getAsString(entry, ++pos, data->plan, MAX_PLAN + 1);

    // Copy price data
    data->price = csv_getAsReal(entry, ++pos);

    // Copy number of devices data
    data->numDevices = csv_getAsInteger(entry, ++pos);
    
    // Init watchlist
    filmstack_init(&data->watchlist);

    // Check preconditions that needs the readed values
    assert(data->price >= 0);
    assert(data->numDevices >= 1);
}

// Copy the data from the source to destination (individual data)
void subscription_cpy(tSubscription* destination, tSubscription source) {
    // Copy subscription's id data
    destination->id = source.id;

    // Copy identity document data
    strncpy(destination->document, source.document, MAX_DOCUMENT + 1);

    // Copy start date
    date_cpy(&(destination->start_date), source.start_date);

    // Copy end date
    date_cpy(&(destination->end_date), source.end_date);

    // Copy plan data
    strncpy(destination->plan, source.plan, MAX_PLAN + 1);

    // Copy price data
    destination->price = source.price;

    // Copy number of devices data
    destination->numDevices = source.numDevices;
    
    filmstack_init(&destination->watchlist);
    
    if (source.watchlist.count>0) {
        tFilmstackNode *pFimStackNodes[source.watchlist.count];
        tFilmstackNode *fimStackNode;
        fimStackNode = source.watchlist.top;
        int j = 0;
        while (fimStackNode != NULL) {
            pFimStackNodes[j] = fimStackNode;
            fimStackNode = fimStackNode->next;
            j++;
        }
        
        //adding the films to the watchlist in reverse order because filmstack_push is used
        for(j=source.watchlist.count -1;j>=0;j--) {
            filmstack_push(&destination->watchlist,pFimStackNodes[j]->elem);
        }
    }
}

// Get subscription data using a string
void subscription_get(tSubscription data, char* buffer) {
    // Print all data at same time
    sprintf(buffer,"%d;%s;%02d/%02d/%04d;%02d/%02d/%04d;%s;%g;%d",
        data.id,
        data.document,
        data.start_date.day, data.start_date.month, data.start_date.year,
        data.end_date.day, data.end_date.month, data.end_date.year,
        data.plan,
        data.price,
        data.numDevices);
}

// Initialize subscriptions data
tApiError subscriptions_init(tSubscriptions* data) {
    // Check input data
    assert(data != NULL);
    data->elems = NULL;
    data->count = 0; 
    
    return E_SUCCESS;
}

// Return the number of subscriptions
int subscriptions_len(tSubscriptions data) {
    return data.count;
}

// Add a new subscription
tApiError subscriptions_add(tSubscriptions* data, tPeople people, tSubscription subscription) {

    // Check input data
    assert(data != NULL);

    // If subscription already exists, return an error
    for (int i=0; i< data->count; i++) {
        if (subscription_equal(data->elems[i], subscription))
            return E_SUBSCRIPTION_DUPLICATED;
    }

    // If the person does not exist, return an error
    if (people_find(people, subscription.document) < 0)
        return E_PERSON_NOT_FOUND;

    // Copy the data to the new position
    if (data->elems == NULL) {
        data->elems = (tSubscription*) malloc(sizeof(tSubscription));
    } else {
        data->elems = (tSubscription*) realloc(data->elems, (data->count + 1) * sizeof(tSubscription));
    }
    assert(data->elems != NULL);
    subscription_cpy(&(data->elems[data->count]), subscription);

    /////////////////////////////////
    // PR3_3f
    /////////////////////////////////
	data->elems[data->count].id = data->count + 1; 

    /////////////////////////////////
    // Increase the number of elements
    data->count++;

    return E_SUCCESS;
}

// Remove a subscription
tApiError subscriptions_del(tSubscriptions* data, int id) {
    int idx;
    int i;
    
    // Check if an entry with this data already exists
    idx = subscriptions_find(*data, id);
    
    // If the subscription does not exist, return an error
    if (idx < 0)
        return E_SUBSCRIPTION_NOT_FOUND;
    
    // Shift elements to remove selected
    for(i = idx; i < data->count-1; i++) {
            // Copy element on position i+1 to position i
            subscription_cpy(&(data->elems[i]), data->elems[i+1]);
            
            /////////////////////////////////
            // PR3_3e
            /////////////////////////////////
			data->elems[i].id = i + 1;
    }
	//free watchlist
    filmstack_free(&data->elems[data->count - 1].watchlist);

    // Update the number of elements
    data->count--;  

    if (data->count > 0) {
        //filmstack_free(&data->elems[data->count].watchlist);
        data->elems = (tSubscription*) realloc(data->elems, data->count * sizeof(tSubscription));
        assert(data->elems != NULL);
    } else {
        subscriptions_free(data);
    }
    
    return E_SUCCESS;
}

// Get subscription data of position index using a string
void subscriptions_get(tSubscriptions data, int index, char* buffer)
{
    assert(index >= 0 && index < data.count);
    subscription_get(data.elems[index], buffer);
}

// Returns the position of a subscription looking for id's subscription. -1 if it does not exist
int subscriptions_find(tSubscriptions data, int id) {
    int i = 0;
    while (i < data.count) {
        if (data.elems[i].id == id) {
            return i;
        }
        i++;
    }

    return -1;
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

// Remove all elements 
tApiError subscriptions_free(tSubscriptions* data) {    
    if (data->elems != NULL) {
    /////////////////////////////////
    // PR2_2b
    /////////////////////////////////
        for (int i = 0; i < data->count; i++) {
            filmstack_free(&data->elems[i].watchlist);
        }
    /////////////////////////////////
        free(data->elems);
    }
    subscriptions_init(data);
    
    return E_SUCCESS;
  
}

// Calculate Vip Level of a person
int calculate_vipLevel(tSubscriptions* data, char* document) {
    /////////////////////////////////
    // PR3_2c
    /////////////////////////////////
    int	vipLevel; 
	int months;
	float totalSubCost = 0.0f;
	tSubscriptions* clientSubs;
	
	assert(data != NULL);
	assert(document != NULL);

	clientSubs = subscriptions_findByDocument(*data, document);
	if (clientSubs == NULL || clientSubs->count == 0) {
		if (clientSubs != NULL) {
			subscriptions_free(clientSubs);
		}
		return 0;
	}

	for (int i = 0; i < clientSubs->count; i++) {
		months = monthsBetween(clientSubs->elems[i].start_date, clientSubs->elems[i].end_date);
		totalSubCost += clientSubs->elems[i].price * (float)months;
	}
	vipLevel = (int)(totalSubCost / 500.00f);

	subscriptions_free(clientSubs);
    return vipLevel;
}

// Update the vipLevel of each person 
tApiError update_vipLevel(tSubscriptions *data, tPeople* people) {
    /////////////////////////////////
    // PR3_2d
    /////////////////////////////////
	assert(data != NULL);
	assert(people != NULL);

	for (int i = 0; i < people->count; i++) {
		people->elems[i].vipLevel = calculate_vipLevel(data, people->elems[i].document); 	
	}
    return E_SUCCESS;
}

// Return a pointer to the longest film of the list
char* popularFilm_find(tSubscriptions data) {
    /////////////////////////////////
    // PR3_3a
    /////////////////////////////////
	if (data.count == 0) return NULL;

	tFilm* films[MAX_FILMS];
	int counts[MAX_FILMS];
	int filmCount = 0;

	for (int i = 0; i < data.count; i++) {
		tFilmstack* stack = &data.elems[i].watchlist;
		tFilmstackNode* node = stack->top;

		while (node != NULL) {
			tFilm* current = &node->elem;

			// Check if we've already seen this film (by name)
			int found = -1;
			for (int k = 0; k < filmCount; k++) {
				if (film_equals(*films[k], *current) == true) {
					found = k;
					break;
				}
			}
			if (found != -1) { 
				counts[found]++; 
			} else { 
				films[filmCount] = current;
				counts[filmCount] = 1;
				filmCount++;
			}
			node = node->next;
		} 
	}

	if (filmCount == 0) {
		return NULL;
	}

	// Find most frequent (break ties with newer release) 
	tFilm* mostPopular = films[0];
	int maxCount = counts[0];

	for (int i = 1; i < filmCount; i++) {
		if (counts[i] > maxCount) { 
			mostPopular = films[i];
			maxCount = counts[i];
		} else if (counts[i] == maxCount) {
			int cmp = date_cmp(films[i]->release, mostPopular->release);
			if (cmp > 0 || (cmp == 0 && strcmp(films[i]->name, mostPopular->name) < 0)) {
				mostPopular = films[i];
			}
		}
	}
	return mostPopular->name;
}

// Return a pointer to the subscriptions of the client with the specified document
tSubscriptions* subscriptions_findByDocument(tSubscriptions data, char* document) {
    /////////////////////////////////
    // PR3_3b
    /////////////////////////////////
	tSubscriptions* subsByDoc;
	int	i;
	int	j;

	assert(&data != NULL);
	assert(document != NULL);
	
	subsByDoc = malloc(sizeof(tSubscriptions));
	subsByDoc->count = 0;
	subsByDoc->elems = NULL;
	if (subsByDoc == NULL) {
		return NULL;
	}
	j = 0;
	while (j < data.count) {
		if (strcmp(data.elems[j].document, document) == 0) {
			subsByDoc->count++;
		}
		j++;
	}
	if (subsByDoc->count == 0) {
		return subsByDoc;
	}
	subsByDoc->elems = malloc(subsByDoc->count * sizeof(tSubscription));
	if (subsByDoc->elems == NULL) {
		subscriptions_free(subsByDoc);
		return NULL;
	}
	i = 0;
	j = 0;
	while (j < data.count) {
		if (strcmp(data.elems[j].document, document) == 0) {
			subscription_cpy(&subsByDoc->elems[i], data.elems[j]);
			/////////////////////////////////
			// PR3_3d
			/////////////////////////////////
			subsByDoc->elems[i].id = i + 1;
			/////////////////////////////////
			i++;
		}
		j++;
	}

    return subsByDoc;
    
}

// return a pointer to the subscription with the specified id
tSubscription* subscriptions_findHash(tSubscriptions data, int id) {
    /////////////////////////////////
    // PR3_3c
    /////////////////////////////////
	assert(&data != NULL);
	assert(id >= 0);

	if (id > data.count) {
		return NULL;
	}
	
	return &data.elems[id - 1];
}

// Compare two subscription
bool subscription_equal(tSubscription subscription1, tSubscription subscription2) {
    
    if (strcmp(subscription1.document,subscription2.document) != 0)
        return false;
    
    if (date_cmp(subscription1.start_date,subscription2.start_date) != 0)
        return false;
        
    if (date_cmp(subscription1.end_date,subscription2.end_date) != 0)
        return false;
    
    if (strcmp(subscription1.plan,subscription2.plan) != 0)
        return false;
        
    if (subscription1.price != subscription2.price)
        return false;
    
    if (subscription1.numDevices != subscription2.numDevices)
        return false;
    
    return true;
}

/*---------AUXILIARY FUNCTIONS---------*/

int	monthsBetween(tDate start, tDate end) {
	return (end.year - start.year) * 12 + (end.month - start.month) + 1;
}
