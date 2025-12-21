package edu.uoc.pac4.alien;

public class AlienException extends Exception {
    public static final String INVALID_ALIEN_NAME = "[ERROR] Alien name " +
            "cannot be null or empty.";
    public static final String INVALID_HAUNTING_POWER = "[ERROR] Haunting " +
            "power must be greater than 0.";
    public static final String INVALID_STEALTH_LEVEL = "[ERROR] Stealth level " +
            "must be between 0 and 100.";
    public static final String INVALID_MORPHING_ABILITY = "[ERROR] Morphing ability " +
            "must be between 0.0 and 1.0.";

    public AlienException(String message) {super(message);}
}
