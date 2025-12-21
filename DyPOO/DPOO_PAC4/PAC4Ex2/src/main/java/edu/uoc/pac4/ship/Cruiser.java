package edu.uoc.pac4.ship;

public class Cruiser extends SpaceShip{
    private static final double CARGO_CAPACITY = 8000.0;

    private int numberOfCannons;

   public Cruiser(String id, int peopleCapacity,
                  SpaceShipRolType spaceShipRolType, int numberOfCannons) throws SpaceShipException{
       super(id, peopleCapacity, CARGO_CAPACITY, spaceShipRolType);
       setNumberOfCannons(numberOfCannons);
   }

   public int getNumberOfCannons() {
       return numberOfCannons;
   }

   public void setNumberOfCannons(int numberOfCannons) throws SpaceShipException {
       if (numberOfCannons < 0) {
           throw new SpaceShipException(SpaceShipException.INVALID_NUMBER_OF_CANNONS);
       }
       this.numberOfCannons = numberOfCannons;
   }

   public String toString(){
       return super.toString().replaceAll("shipAttr",
               "\"numberOfCannons\": " + numberOfCannons);
   }
}
