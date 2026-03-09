package edu.uoc.uocycle.model.trip;

public class TripException extends Exception {
    public static final String NULL_BICYCLE = "Bicycle cannot be null.";
    public static final String NULL_START_STATION = "Start station cannot be null.";
    public static final String START_TIME_AFTER_NOW = "Start time cannot be " +
            "after now.";
    public static final String END_TIME_BEFORE_START_TIME = "End time must be after start time.";
    public static final String DISTANCE_LESS_THAN_ZERO = "Distance must be greater than or equal to 0.0.";
    public TripException(String message) {
        super(message);
    }
}
