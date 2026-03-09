package edu.uoc.uocycle.controller;

import edu.uoc.uocycle.model.bicycle.*;
import edu.uoc.uocycle.model.station.Station;
import edu.uoc.uocycle.model.station.StationException;
import edu.uoc.uocycle.model.trip.Trip;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UOCycleController {

    private Trip currentTrip;
    private String userName;

    private final Map<String, Station> stations;
    private final Map<String, Bicycle> bicycles;
    private final List<Trip> trips;

    /**
     * Constructor
     * Initializes the attributes and loads the data from the files
     * @param stationsFilePath Path to the stations data file
     * @param bicyclesFilePath Path to the bicycles data file
     * @param bicyclesStationOriginFilePath Path to the bicycles-station origin data file
     * @throws Exception If an error occurs while loading the data
     */
    public UOCycleController(String stationsFilePath, String bicyclesFilePath, String bicyclesStationOriginFilePath) throws Exception {
        stations = new LinkedHashMap<>();
        bicycles = new HashMap<>();
        trips = new ArrayList<>();
        loadStations(stationsFilePath);
        loadBicycles(bicyclesFilePath, bicyclesStationOriginFilePath);
    }

    /**
     * Reads a table from a file
     * @param filename Name of the file to read
     * @param expectedParts Expected number of parts per line (0 for any)
     * @return List of string arrays, each array representing a line split by '|'
     */
    private List<String[]> readTable(String filename, int expectedParts) {
        List<String[]> rows = new ArrayList<>();
        String path = "/data/" + filename;

        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                System.err.println("File not found: " + path);
                return rows;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                int lineNo = 0;
                while ((line = br.readLine()) != null) {
                    lineNo++;
                    String raw = line;
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;

                    String[] parts = line.split("\\|", -1);
                    if (expectedParts > 0 && parts.length != expectedParts) {
                        System.err.printf("Invalid line format (%s:%d): %s%n", filename, lineNo, raw);
                        continue;
                    }
                    for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
                    rows.add(parts);
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading file " + path + ": " + e.getMessage());
        }
        return rows;
    }

    /**
     * Loads stations from file
     * @param filename Name of the file containing station data
     * @throws Exception If an error occurs while loading stations
     */
    private void loadStations(String filename) throws Exception {
        // id|lat|lon|address|maxCapacity
        for (String[] parts : readTable(filename, 5)) {
            String id = parts[0];
            double latitude = Double.parseDouble(parts[1]);
            double longitude = Double.parseDouble(parts[2]);
            String address = parts[3];
            int maxCapacity = Integer.parseInt(parts[4]);
            addStation(id, latitude, longitude, address, maxCapacity);
        }
    }

    /**
     * Loads bicycles from files
     * @param bicyclesFilePath Path to the bicycles data file
     * @param bicyclesStationOriginFilePath Path to the bicycles-station origin data file
     * @throws Exception If an error occurs while loading bicycles
     */
    private void loadBicycles(String bicyclesFilePath, String bicyclesStationOriginFilePath) throws Exception {
        // type|args
        for (String[] parts : readTable(bicyclesFilePath, 2)) {
            addBicycle(parts[0], parts[1]);
        }
        // bicycleId|stationId
        for (String[] parts : readTable(bicyclesStationOriginFilePath, 2)) {
            addBicycleToStation(parts[0], parts[1]);
        }
    }

    /**
     * Adds a station to the list
     * @param id Station ID
     * @param latitude Station location latitude
     * @param longitude Station location longitude
     * @param address Station location address
     * @param maxCapacity Station maximum capacity
     * @throws Exception If an error occurs while creating the station or adding it to the list
     */
    public void addStation(String id, double latitude, double longitude, String address, int maxCapacity) throws Exception {
        Station station = new Station(id, latitude, longitude, address, maxCapacity);
        stations.put(id, station);
    }

    /**
     * Adds a bicycle to the hashtable
     * @param type Type of bicycle (MECHANICAL or ELECTRICAL)
     * @param args Arguments for the bicycle constructor
     * @throws Exception If an error occurs while creating the bicycle or adding it to the hashtable
     */
    public void addBicycle(String type, String args) throws Exception {
        //TODO
        Bicycle bicycle;
        String[] parts = args.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        String id = parts[0];
        AvailabilityStatus status = AvailabilityStatus.valueOf(parts[1].toUpperCase());
        float weight = Float.parseFloat(parts[2]);
        LocalDate registrationDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastMaintenanceDate = LocalDate.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (type.equals("MECHANICAL")) {
            int gearCount = Integer.parseInt(parts[5]);
            GearType gearType = GearType.valueOf(parts[6].toUpperCase());
            boolean hasBasket = Boolean.parseBoolean(parts[7]);
            bicycle = new MechanicalBicycle(id, status ,weight, registrationDate, lastMaintenanceDate, gearCount, gearType,
                    hasBasket);
        }
        else if (type.equals("ELECTRICAL")) {
            int batteryCapacity = Integer.parseInt(parts[5]);
            int batteryVoltage = Integer.parseInt(parts[6]);
            float batteryCurrent = Float.parseFloat(parts[7]);
            int motorPower = Integer.parseInt(parts[8]);
            float motorSpeed = Float.parseFloat(parts[9]);
            boolean motorHasRegenerativeBraking = Boolean.parseBoolean(parts[10]);
            bicycle = new ElectricalBicycle(id, status, weight, registrationDate, lastMaintenanceDate, batteryCapacity, batteryVoltage, batteryCurrent, motorPower, motorSpeed, motorHasRegenerativeBraking);
        }
        else {
            throw new UOCycleControllerException(UOCycleControllerException.INVALID_BICYCLE_TYPE);
        }
        bicycles.put(id, bicycle);
    }

    /**
     * Adds a bicycle to a station
     * @param bicycleId ID of the bicycle to add
     * @param stationId ID of the station to which the bicycle will be added
     * @throws Exception If the bicycle or station is not found, or if an error occurs while adding the bicycle to the station
     */
    public void addBicycleToStation(String bicycleId, String stationId) throws Exception {
        Bicycle bicycleFound = null;

        for (Bicycle bicycle : bicycles.values()) {
            if (bicycleId.equals(bicycle.getId())) {
               bicycleFound = bicycle;
            }
        }
        if (bicycleFound == null) {
            throw new UOCycleControllerException(UOCycleControllerException.BICYCLE_ID_NOT_FOUND);
        }
        for (Station station : stations.values()) {
            if (stationId.equals(station.getId())) {
               station.addBicycle(bicycleFound);
               return;
            }
        }
        throw new UOCycleControllerException(UOCycleControllerException.STATION_ID_NOT_FOUND);
    }

    /**
     * Returns the list of stations as an array
     * @return Array of stations
     */
    public Object[] getStations() {
        return stations.values().toArray();
    }

    /**
     * Returns the list of bicycles as an array
     * @return Array of bicycles
     */
    public Object[] getBicycles() {
        return bicycles.values().toArray();
    }

    /**
     * Returns the bicycles of a station as an array
     * @return Array of bicycles in the specified station
     * @throws Exception If the station is not found
     */
    public Object[] getBicyclesByStation(String stationId) throws Exception {
        for (Station station : stations.values()) {
            if (station.getId().equals(stationId)) {
                Map<String, Bicycle> bicyclesMap = station.getBicycles();
                Object[] result = new Object[bicyclesMap.size()];
                int i = 0;
                for(Bicycle bicycle : bicyclesMap.values()) {
                    result[i] = bicycle.toString();
                    i++;
                }
                return result;
            }
        }
        throw new UOCycleControllerException(UOCycleControllerException.STATION_ID_NOT_FOUND);
    }

    /**
     * Creates a new user
     * @param userName Name of the user
     * @throws Exception If an error occurs while creating the user
     */
    public void createUser(String userName) throws Exception {
        if (userName == null || userName.isBlank()) {
            throw new UOCycleControllerException(UOCycleControllerException.NULL_OR_BLANK_USER);
        }
        this.userName = userName;
    }

    /**
     * Returns the current username
     * @return Current username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Starts a trip by selecting the start station and bicycle
     * @param stationId The ID of the start station
     * @param bicycleId The ID of the selected bicycle
     * @throws Exception If the station or user is not found, or if an error occurs while starting the trip
     */
    public void startTrip(String stationId, String bicycleId) throws Exception {
        if (stationId == null || stationId.isBlank()) {
            throw new UOCycleControllerException(UOCycleControllerException.NULL_OR_BLANK_STATION_ID);
        }
        if (bicycleId == null || bicycleId.isBlank()) {
            throw new UOCycleControllerException(UOCycleControllerException.NULL_OR_BLANK_BICYCLE_ID);
        }
        if (userName == null || userName.isBlank()) {
            throw new UOCycleControllerException(UOCycleControllerException.NULL_OR_BLANK_USER);
        }
        if (isTripStarted()) {
            throw new UOCycleControllerException(UOCycleControllerException.CURRENT_TRIP_ALREADY_IN_PROGRESS);
        }
        for (Station station : stations.values()) {
            if (stationId.equals(station.getId())) {
                Map<String, Bicycle> bicycles = station.getBicycles();
                for (Bicycle bicycle : bicycles.values()) {
                    if (bicycle.getId().equals(bicycleId) && bicycle.getStatus() == AvailabilityStatus.AVAILABLE) {
                        currentTrip = new Trip(bicycle, station, null , LocalDateTime.now(), null);
                        trips.add(currentTrip);
                        station.removeBicycle(bicycle);
                        return;
                    }
                }
            }
        }
        throw new UOCycleControllerException(UOCycleControllerException.STATION_ID_NOT_FOUND);
    }

    /**
     * Ends the current trip by selecting the end station
     * @param stationId The ID of the end station
     * @return A JSON string with the trip summary
     * @throws Exception If the station or user is not found, or if an error occurs while ending the trip
     */
    public String endTrip(String stationId) throws Exception {
        if (!isTripStarted()) {
            throw new UOCycleControllerException(UOCycleControllerException.NO_CURRENT_TRIP);
        }
        if (userName == null || userName.isBlank()) {
            throw new UOCycleControllerException(UOCycleControllerException.NULL_OR_BLANK_USER);
        }
        for (Station station : stations.values()) {
            if (stationId.equals(station.getId())) {
                if (!station.hasAvailableSpace()) {
                    throw new StationException(StationException.STATION_AT_MAX_CAPACITY);
                }
                currentTrip.setEndStation(station);
                currentTrip.setEndTime(LocalDateTime.now());
                currentTrip.setDistance();
                station.addBicycle(currentTrip.getBicycle());
                String result = currentTrip.toString();
                currentTrip = null;
                return result;
            }
        }
        throw new UOCycleControllerException(UOCycleControllerException.STATION_ID_NOT_FOUND);
    }

    /**
     * Checks if a trip has been started
     * @return True if a trip has been started, otherwise false
     */
    public boolean isTripStarted() {
       return currentTrip != null;
    }

    /**
     * Returns the current trip of the user as a JSON string
     * @return Current trip in JSON format
     */
    public String getCurrentTrip() {
        if (userName == null || !isTripStarted()) {
            return null;
        }
        return currentTrip.toString();
    }

    /**
     * Returns the trips of the current user as an array of strings
     * @return Array of trips in JSON format
     */
    public String[] getTrips() {
        //TODO
        int tripCount = trips.size();
        String[] tripsArray = new String[tripCount];
        for (int i = 0; i < tripCount; i++) {
            tripsArray[i] = trips.get(i).toString();
        }
        return tripsArray;
    }
}