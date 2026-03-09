package edu.uoc.uocycle.model.bicycle;

public enum GearType {
    FIXED_GEAR("Fixed Gear"),
    HUB("Hub"),
    SINGLE_SPEED("Single Speed"),
    DERAILLEUR("Derailleur");

    private final String description;

    /**
     * Constructor
     * Initializes the description for JSON stringification.
     */
    GearType(String description) {
        this.description = description;
    }

    /**
     * Used for JSON stringification.
     * @return formatted string for JSON stringification.
     */
    public String getDescription() {
        return description;
    }
}
