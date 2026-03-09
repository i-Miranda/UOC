package edu.uoc.uocycle.model.station;

public class Location {
    private final double latitude;
    private final double longitude;
    private final String address;

    /**
     * Constructor
     * @param latitude The Location's latitude
     * @param longitude The Location's longitude
     * @param address The Location's address
     * @throws StationException If there is an error while setting the data
     */
    public Location(double latitude, double longitude, String address) throws StationException {
        if (latitude < -90.0 || latitude > 90.0) {
            throw new StationException(StationException.INVALID_LATITUDE_RANGE);
        }
        this.latitude = latitude;
        if (longitude < -180.0 || longitude > 180.0) {
            throw new StationException(StationException.INVALID_LONGITUDE_RANGE);
        }
        this.longitude = longitude;
        if (address == null || address.isBlank()) {
            throw new StationException(StationException.INVALID_ADDRESS);
        }
        this.address = address;
    }

    /**
     * The Location's latitude getter
      * @return The Location's latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * The Location's longitude getter
      * @return The Location's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * The Location's address getter
     * @return The Location's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
   @Override
    public String toString() {
       return "{" +
               "\"latitude\":" + getLatitude() + "," +
               "\"longitude\":" + getLongitude() + "," +
               "\"address\":\"" + getAddress() + "\"" +
               "}";
   }
}
