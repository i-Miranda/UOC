package edu.uoc.pac4.ship;

public class DeathStar extends SpaceShip{
    private static final int PEOPLE_CAPACITY = 10000;
    private static final double CARGO_CAPACITY = 5000.0;

    private double laserPower;

    public DeathStar(String id, SpaceShipRolType spaceShipRolType,
                     double laserPower) throws SpaceShipException {
        super(id, PEOPLE_CAPACITY, CARGO_CAPACITY, spaceShipRolType);
        setLaserPower(laserPower);
    }

    public double getLaserPower() {
        return laserPower;
    }

    public void setLaserPower(double laserPower) throws SpaceShipException {
        if (laserPower <= 0) {
            throw new SpaceShipException(SpaceShipException.INVALID_LASER_POWER);
        }
        this.laserPower = laserPower;
    }

    @Override
    public String toString() {
        return super.toString().replaceAll("shipAttr",
                "\"laserPower\": " + String.format("%.2f", laserPower));
    }
}
