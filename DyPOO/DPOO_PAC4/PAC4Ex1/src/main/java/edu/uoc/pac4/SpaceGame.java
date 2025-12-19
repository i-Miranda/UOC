package edu.uoc.pac4;

import edu.uoc.pac4.ship.SpaceShip;
import java.util.List;
import java.util.ArrayList;

public class SpaceGame {
    private final static int MAX_SPACESHIPS = 10;
    private String galaxyName;

    private List<SpaceShip> spaceships;

    public SpaceGame(String galaxyName) throws SpaceGameException {
        setGalaxyName(galaxyName);
        spaceships = new ArrayList<>();
    }

    public String getGalaxyName() {
        return galaxyName;
    }

    public void setGalaxyName(String galaxyName) throws SpaceGameException {
        if (galaxyName == null || galaxyName.isBlank()) {
            throw new SpaceGameException(SpaceGameException.INVALID_GALAXY_NAME);
        }
        this.galaxyName = galaxyName;
    }

    public List<SpaceShip> getSpaceships() {
        return spaceships;
    }

    public int getMaxSpaceships() {
        return MAX_SPACESHIPS;
    }

    public boolean addSpaceShip(SpaceShip spaceShip) throws SpaceGameException {
        if (spaceShip == null) {
            throw new SpaceGameException(SpaceGameException.NULL_SPACESHIP);
        }
        else if (spaceships.contains(spaceShip)) {
            throw new SpaceGameException(SpaceGameException.SPACESHIP_ALREADY_EXISTS);
        }
        else if (spaceships.size() >= MAX_SPACESHIPS) {
            throw new SpaceGameException(SpaceGameException.MAX_SPACESHIPS_REACHED);
        }
        return spaceships.add(spaceShip);
    }

    public boolean removeSpaceShip(SpaceShip spaceShip) throws SpaceGameException {
        if (spaceShip == null) {
            throw new SpaceGameException(SpaceGameException.NULL_SPACESHIP);
        }
        else if (spaceships.contains(spaceShip)) {
            return spaceships.remove(spaceShip);
        }
        else {
            throw new SpaceGameException(SpaceGameException.SPACESHIP_NOT_FOUND);
        }
    }
    @Override
    public String toString() {
        StringBuilder spaceshipsString = new StringBuilder();
        for (int i = 0; i < spaceships.size(); i++) {
            spaceshipsString.append(spaceships.get(i).toString());
            if (i < spaceships.size() - 1) {
                spaceshipsString.append(",");
            }
        }
        return "{\"galaxyName\": \"" + galaxyName + "\", \"spaceships\": [" +
                spaceshipsString + "]}";
    }
}
