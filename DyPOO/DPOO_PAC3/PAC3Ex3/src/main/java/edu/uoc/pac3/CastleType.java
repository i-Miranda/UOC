package edu.uoc.pac3;

public enum CastleType {
    FORTRESS("Fortress", 40, 20),
    ROYAL_PALACE("Royal palace", 150, 50),
    STRONGHOLD("Stronghold", 400, 250),
    CITADEL("Citadel", 800, 500);

    private final static double SOLDIER_VALUE = 1.5;
    private final static double ARCHER_VALUE = 2.0;
    private final String description;
    private final int soldiers;
    private final int archers;
    CastleType(String description, int soldiers, int archers) {
        this.description = description;
        this.soldiers = soldiers;
        this.archers = archers;
    }

    public String getDescription() {
        return description;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public int getArchers() {
        return archers;
    }

    public double getTotalValue() {
        double soldiersValue = (double)soldiers * SOLDIER_VALUE;
        double archersValue = (double)archers * ARCHER_VALUE;

        return soldiersValue + archersValue;
    }

    public static CastleType getType(String description) {
        return switch (description) {
            case "Fortress" -> CastleType.FORTRESS;
            case "Royal palace" -> CastleType.ROYAL_PALACE;
            case "Stronghold" -> CastleType.STRONGHOLD;
            case "Citadel" -> CastleType.CITADEL;
            default -> null;
        };
    }

    public CastleType nextType() {
        return switch (this) {
            case FORTRESS -> CastleType.ROYAL_PALACE;
            case ROYAL_PALACE -> CastleType.STRONGHOLD;
            case STRONGHOLD, CITADEL -> CastleType.CITADEL;
        };
    }
}
