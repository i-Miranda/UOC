package edu.uoc.pac4.ship;

public class SpaceShipException extends Exception {
    public static final String INVALID_SPACESHIP_ID = "[ERROR] Spaceship ID " +
            "cannot be null and have to start with 'SS-'.";
    public static final String INVALID_SPACESHIP_ROL_TYPE = "[ERROR] Spaceship " +
            "type cannot be null.";
    public static final String INVALID_LASER_POWER = "[ERROR] Laser power " +
            "must be greater than 0.";
    public static final String INVALID_NUMBER_OF_CANNONS = "[ERROR] Number of" +
            " cannons cannot be negative.";
    public static final String NULL_ALIEN = "[ERROR] Alien cannot be null.";
    public static final String ALIEN_ALREADY_EXISTS = "[ERROR] Alien already " +
            "exists in the spaceship.";
    public static final String PEOPLE_CAPACITY_EXCEEDED = "[ERROR] People " +
            "capacity exceeded.";
    public SpaceShipException(String message) {
        super(message);
    }
}
