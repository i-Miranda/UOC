package edu.uoc.pac4;

public class SpaceGameException extends Exception {
    public static final String INVALID_GALAXY_NAME = "[ERROR] Galaxy name " +
            "cannot be null or empty.";
    public static final String NULL_SPACESHIP = "[ERROR] Spaceship " +
            "cannot be null.";
    public static final String MAX_SPACESHIPS_REACHED = "[ERROR] Maximum " +
            "number of spaceships reached.";
    public static final String SPACESHIP_ALREADY_EXISTS = "[ERROR] Spaceship " +
            "already exists in the game.";
    public static final String SPACESHIP_NOT_FOUND = "[ERROR] Spaceship " +
            "not found in the game.";
    public SpaceGameException(String message) {
        super(message);
    }
}
