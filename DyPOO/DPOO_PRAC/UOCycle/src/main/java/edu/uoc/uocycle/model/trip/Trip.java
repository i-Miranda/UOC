package edu.uoc.uocycle.model.trip;

import edu.uoc.uocycle.model.bicycle.Bicycle;
import edu.uoc.uocycle.model.station.Station;

import java.time.LocalDateTime;

public class Trip {
    private Bicycle bicycle;
    private Station startStation;
    private Station endStation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double distance;

    /**
     * Constructor
     * @param bicycle The Bicycle used during the Trip
     * @param startStation The Trip's starting Station
     * @param endStation The Trip's ending Station
     * @param startTime The Trip's start time
     * @param endTime The Trip's end time
     * @throws TripException if there is an error while setting the data.
     */
    public Trip(Bicycle bicycle, Station startStation, Station endStation,
                LocalDateTime startTime, LocalDateTime endTime) throws TripException {
        setBicycle(bicycle);
        setStartStation(startStation);
        setStartTime(startTime);
        setEndStation(endStation);
        setEndTime(endTime);
        setDistance();
    }

    /**
     * The Trip's Bicycle getter
      * @return The Trip's Bicycle
     */
    public Bicycle getBicycle() {
        return bicycle;
    }

    /**
     * The Trip's Bicycle setter
      * @param bicycle The desired Bicycle
     * @throws TripException Bicycle cannot be NULL
     */
    private void setBicycle(Bicycle bicycle) throws TripException {
        if (bicycle == null) {
            throw new TripException(TripException.NULL_BICYCLE);
        }
        this.bicycle = bicycle;
    }

    /**
     * The Trip's start Station getter
      * @return The Trip's start Station
     */
    public Station getStartStation() {
        return startStation;
    }

    /**
     * The Trip's start Station setter
      * @param startStation The desired start Station
     * @throws TripException The startStation cannot be NULL.
     */
    private void setStartStation(Station startStation) throws TripException {
        if (startStation == null) {
            throw new TripException(TripException.NULL_START_STATION);
        }
        this.startStation = startStation;
    }

    /**
     * The Trip's end Station getter
      * @return The Trip's end Station
     */
    public Station getEndStation() {
        return endStation;
    }

    /**
     * The Trip's end Station setter
      * @param endStation The desired end Station
     */
    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    /**
     * The Trip's start time getter
      * @return The Trip's startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * The Trip's start time setter
      * @param startTime The desired start time
     * @throws TripException The desired start time cannot be later than the current date and time
     */
    private void setStartTime(LocalDateTime startTime) throws TripException {
        if (startTime != null) {
            if (startTime.isAfter(LocalDateTime.now())) {
                throw new TripException(TripException.START_TIME_AFTER_NOW);
            }
            this.startTime = startTime;
        } else {
            this.startTime = LocalDateTime.now();
        }
    }

    /**
     * The Trip's end time getter
     * @return The Trip's endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * The Trip's end time setter
      * @param endTime The desired end time
     * @throws TripException The end time cannot be before or equal the start time
     */
    public void setEndTime(LocalDateTime endTime) throws TripException {
        if (endTime != null) {
            if (endTime.isBefore(startTime) || endTime.isEqual(startTime)) {
                throw new TripException(TripException.END_TIME_BEFORE_START_TIME);
            }
            this.endTime = endTime;
        } else {
            this.endTime = LocalDateTime.now();
        }
    }

    /**
     * The Trip's distance getter
      * @return The Trip's distance in km.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * The helper function used to calculate the Trip's distance in km. Uses Haversine algorithm
      * @param lat1 The starting latitude
     * @param lon1 The starting longitude
     * @param lat2 The ending latitude
     * @param lon2 The ending longitude
     * @return The Trip's distance between the startStation and endStation in km.
     */
    private static double haversineKm(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // km
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dPhi = Math.toRadians(lat2 - lat1);
        double dLambda = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dPhi / 2) * Math.sin(dPhi / 2)
                + Math.cos(phi1) * Math.cos(phi2)
                * Math.sin(dLambda / 2) * Math.sin(dLambda / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    /**
     * The Trip's distance setter. Uses the Haversine helper function.
     * If the distance is 0.0f, than Trip is in progress.
      * @throws TripException The distance cannot be less than 0.0f.
     */
    public void setDistance() throws TripException {
        if (!hasEnded())
            return;
        double startLatitude = getStartStation().getLocation().getLatitude();
        double startLongitude = getStartStation().getLocation().getLongitude();
        double endLatitude = getEndStation().getLocation().getLatitude();
        double endLongitude = getEndStation().getLocation().getLongitude();
        this.distance = haversineKm(startLatitude, startLongitude, endLatitude, endLongitude);
        if (distance < 0.0) {
            throw new TripException(TripException.DISTANCE_LESS_THAN_ZERO);
        }
    }

    /**
     * Check's if the Trip has an end time and end station. If so, it has ended.
      * @return if the Trip has ended.
     */
    public boolean hasEnded() {
        return endTime != null && endStation != null;
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
   @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("bicycle:").append(getBicycle().toString()).append(",");
        sb.append("startStation:").append(getStartStation().toString()).append(",");
        sb.append("startTime:\"").append(getStartTime().toString()).append("\",");
        if (hasEnded()) {
            sb.append("endStation:").append(getEndStation().toString()).append(",");
            sb.append("endTime:\"").append(getEndTime().toString()).append("\",");
        }
        sb.append("distance:").append(String.format("%.2f", getDistance()));
        sb.append("}");
        return sb.toString();
    }
}