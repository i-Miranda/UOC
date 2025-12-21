package edu.uoc.pac4.ship;

public enum SpaceShipRolType {
    BATTLE,
    SCIENCE,
    DIPLOMATIC;

    @Override
    public String toString() {
        String upper = name().substring(0, 1);
        String lower = name().substring(1).toLowerCase();
        return upper + lower;
    }
}
