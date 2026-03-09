package edu.uoc.uocycle.model.bicycle;

public enum AvailabilityStatus {
    AVAILABLE("Available"),
    MAINTENANCE("Maintenance"),
    LOST("Lost"),
    RESERVED("Reserved");

    private final String description;

    /**
     * Constructor
     * Initializes the description for JSON stringification.
     */
    AvailabilityStatus(String description) {
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
