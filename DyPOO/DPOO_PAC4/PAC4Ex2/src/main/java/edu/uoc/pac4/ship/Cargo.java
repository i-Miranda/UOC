package edu.uoc.pac4.ship;

public class Cargo extends SpaceShip{
    private static final int PEOPLE_CAPACITY = 500;

    private boolean isRefrigerated;

    public Cargo(String id, double cargoCapacity,
                 SpaceShipRolType spaceShipRolType, boolean isRefrigerated) throws SpaceShipException {
        super(id, PEOPLE_CAPACITY, cargoCapacity, spaceShipRolType);
        setRefrigerated(isRefrigerated);
    }

    public boolean isRefrigerated() {
        return isRefrigerated;
    }

    public void setRefrigerated(boolean isRefrigerated) {
        this.isRefrigerated = isRefrigerated;
    }

    public String toString(){
        int boolToInt = isRefrigerated ? 1 : 0;
        return super.toString().replaceAll("shipAttr",
                "\"isRefrigerated\": " + boolToInt);
    }
}
