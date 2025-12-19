package edu.uoc.pac4.ship;

public class SpaceShipException extends Exception {
    public static final String INVALID_SPACESHIP_ID = "[ERROR] Spaceship ID " +
            "cannot be null and have to start with 'SS-'.";
    public static final String INVALID_SPACESHIP_ROL_TYPE = "[ERROR] Spaceship " +
            "type cannot be null.";
    public SpaceShipException(String message) {
        super(message);
    }
}
