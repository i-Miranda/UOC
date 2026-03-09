package edu.uoc.uocycle.model.station;

import edu.uoc.uocycle.model.bicycle.Bicycle;

import java.util.Map;
import java.util.LinkedHashMap;

public class Station {
    private String id;
    private Location location;
    private int maxCapacity;

    private final Map<String, Bicycle> bicycles;

    /**
     * Constructor
      * @param id The Station's unique id
     * @param latitude The Location's latitude
     * @param longitude The Location's longitude
     * @param address The Location's address
     * @param maxCapacity The Station's max capacity
     * @throws StationException If there is an error while setting the data
     */
    public Station(String id, double latitude, double longitude, String address,
                   int maxCapacity) throws StationException {
        setId(id);
        setLocation(latitude, longitude, address);
        setMaxCapacity(maxCapacity);
        bicycles = new LinkedHashMap<>();
    }

    /**
     * The Station's id getter
      * @return The Station's id
     */
    public String getId() {
        return id;
    }

    /**
     * The Station's id setter
      * @param id The Station's id
     * @throws StationException The id cannot be null or blank
     */
    private void setId(String id) throws StationException {
        if (id == null || id.isBlank()) {
            throw new StationException(StationException.INVALID_ID);
        }
        this.id = id;
    }

    /**
     * The Location's getter
      * @return The Station's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * The Location's setter
      * @param latitude The Location's latitude
     * @param longitude The Location's longitude
     * @param address The Location's address
     * @throws StationException if the location's constructor fails.
     */
    private void setLocation(double latitude, double longitude, String address) throws StationException {
        location = new Location(latitude, longitude, address);
    }

    /**
     * The Station's max capacity getter
      * @return The Station's max capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * The Sattion's max capacity setter
      * @param maxCapacity The desired max capacity
     * @throws StationException If the maxCapacity is less than or equal to 0.
     */
    private void setMaxCapacity(int maxCapacity) throws StationException {
        if (maxCapacity <= 0) {
            throw new StationException(StationException.INVALID_MAX_CAPACITY);
        }
        this.maxCapacity = maxCapacity;
    }

    /**
     * The Station's bicycles hashmap getter
      * @return The Station's bicycles hashmap
     */
    public Map<String, Bicycle> getBicycles() {
        return bicycles;
    }

    /**
     * Checks if the Station is at max capacity
      * @return true if space is available.
     */
    public boolean hasAvailableSpace() {
        return bicycles.size() < getMaxCapacity();
    }

    /**
     * Adds a Bicycle to the Station's bicycles hashmap
      * @param bicycle The Bicycle to be added
     * @throws StationException If the Bicycle is NULL, if there is no available space in the station,
     * or if the Bicycle is already in the station.
     */
    public void addBicycle(Bicycle bicycle) throws StationException {
        if (bicycle == null) {
            throw new StationException(StationException.NULL_BICYCLE);
        }
        if(!hasAvailableSpace()) {
            throw new StationException(StationException.STATION_AT_MAX_CAPACITY);
        }
        for (Bicycle b : bicycles.values()) {
            if (b.getId().equals(bicycle.getId())) {
                throw new StationException(StationException.BICYCLE_ALREADY_IN_STATION);
            }
        }
        bicycles.put(bicycle.getId(), bicycle);
    }

    /**
     * Removes a Bicycle from the Station's bicycles hashmap
      * @param bicycle The Bicycle to be removed
     * @throws StationException if the Bicycle is NULL or the Bicycle is not in the Station's bicycle hashmap.
     */
    public void removeBicycle(Bicycle bicycle) throws StationException {
        if (bicycle == null) {
            throw new StationException(StationException.NULL_BICYCLE);
        }
        for (Bicycle b : bicycles.values()) {
            if (b.getId().equals(bicycle.getId())) {
                bicycles.remove(b.getId());
                return;
            }
        }
        throw new StationException(StationException.BICYCLE_NOT_IN_STATION);
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
    @Override
    public String toString() {
        return "{" +
        "\"id\":\"" + getId() + "\"," +
        "\"location\":" + location.toString() + "," +
        "\"MAX_CAPACITY\":" + getMaxCapacity() +
        "}";
    }
}