package edu.uoc.uocycle.model.station;

public class StationException extends Exception {
    public static final String INVALID_ID = "Station ID cannot be null or " +
            "blank";
    public static final String INVALID_LATITUDE_RANGE = "Latitude range must " +
            "be in between -90.0 and 90.0.";
    public static final String INVALID_LONGITUDE_RANGE = "Longitude range " +
            "must be in between -180.0 and 180.0.";
    public static final String INVALID_ADDRESS = "Address cannot be null or " +
            "blank.";
    public static final String INVALID_MAX_CAPACITY = "Max capacity cannot be less than or equal to 0.";
    public static final String NULL_BICYCLE = "Bicycle cannot be null.";
    public static final String STATION_AT_MAX_CAPACITY = "Station is at max capacity.";
    public static final String BICYCLE_ALREADY_IN_STATION = "Bicycle already " +
            "in station";
    public static final String BICYCLE_NOT_IN_STATION = "Bicycle not in station";
    public StationException(String message) {
        super(message);
    }
}
