package edu.uoc.uocycle.controller;

public class UOCycleControllerException extends Exception {
    public static final String NULL_OR_BLANK_FILENAME = "Filename cannot be " +
            "null or blank.";
    public static final String INVALID_BICYCLE_TYPE = "Unknown bicycle type.";
    public static final String BICYCLE_ID_NOT_FOUND = "Bicycle id not found.";
    public static final String STATION_ID_NOT_FOUND = "Station id not found.";
    public static final String NO_CURRENT_TRIP = "No current trip in progress.";
    public static final String CURRENT_TRIP_ALREADY_IN_PROGRESS = "Current trip already in progress.";
    public static final String NULL_OR_BLANK_USER = "User cannot be null or " +
            "blank.";
    public static final String NULL_OR_BLANK_STATION_ID = "Station id cannot be null or blank.";
    public static final String NULL_OR_BLANK_BICYCLE_ID = "Bicycle id cannot " +
            "be null or blank.";
    public UOCycleControllerException(String message) {
        super(message);
    }
}
