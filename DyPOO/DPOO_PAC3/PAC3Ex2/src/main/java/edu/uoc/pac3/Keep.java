package edu.uoc.pac3;

public class Keep {
    private final static double MIN_HEIGHT = 15.0;
    public final static String INVALID_LORD_NAME = "[ERROR] Lord name cannot " +
            "be null, empty or formed by only whitespace";
    public final static String INVALID_FLOORS = "[ERROR] Keep must have at " +
            "least one floor";
    public final static String INVALID_HEIGHT = "[ERROR] Height must be at " +
            "least 15.0 meters";
    private String lordName;
    private int floors;
    private double height;
    private int numGuards;
    private boolean hasDungeon;

    public Keep(String lordName, int floors, double height, int numGuards,
                boolean hasDungeon) throws IllegalArgumentException {
        setLordName(lordName);
        setFloors(floors);
        setHeight(height);
        setNumGuards(numGuards);
        setHasDungeon(hasDungeon);
    }

    private void setLordName(String lordName) throws IllegalArgumentException {
        if (lordName == null || lordName.trim().length() == 0) {
            throw new IllegalArgumentException(INVALID_LORD_NAME);
        }
        this.lordName = lordName.trim();
    }

    public String getLordName() {
        return lordName;
    }

    private void setFloors(int floors) throws IllegalArgumentException {
        if (floors <= 0) {
            throw new IllegalArgumentException(INVALID_FLOORS);
        }
        this.floors = floors;
    }

    public int getFloors() {
        return floors;
    }

    private void setHeight(double height) throws IllegalArgumentException {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_HEIGHT);
        }
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    private void setNumGuards(int numGuards) {
        if (numGuards < 0) {
            this.numGuards = 0;
        } else {
            this.numGuards = numGuards;
        }
    }

    public int getNumGuards() {
        return numGuards;
    }

    private void setHasDungeon(boolean hasDungeon) {
        this.hasDungeon = hasDungeon;
    }

    public boolean isHasDungeon() {
        return hasDungeon;
    }
}
