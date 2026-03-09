package edu.uoc.uocycle.model.bicycle;

import java.time.LocalDate;

public class MechanicalBicycle extends Bicycle {
    private int gearCount;
    private GearType gearType;
    private boolean hasBasket;

    /**
     * Constructor
     * @param id The MechanicalBicycle's unique id
     * @param status The MechanicalBicycle's AvailabilityStatus
     * @param weight The MechanicalBicycle's weight
     * @param registrationDate The MechanicalBicycle's registration date
     * @param lastMaintenanceDate The MechanicalBicycle's last maintenance date
     * @param gearCount The MechanicalBicycle's gear count
     * @param gearType The MechanicalBicycle's GearType
     * @param hasBasket If the MechanicalBicycle has a basket
     * @throws BicycleException If there is an error while setting the data
     */
    public MechanicalBicycle(String id, AvailabilityStatus status, float weight,
        LocalDate registrationDate, LocalDate lastMaintenanceDate, int gearCount,
        GearType gearType, boolean hasBasket) throws BicycleException {
        super(id, status, weight, registrationDate, lastMaintenanceDate);
        setGearCount(gearCount);
        setGearType(gearType);
        setHasBasket(hasBasket);
    }

    /**
     * The MechanicalBicycle's gearCount getter
      * @return The MechanicalBicycle's gearCount
     */
    public int getGearCount() {
        return gearCount;
    }

    /**
     * The MechanicalBicycle's gearCount setter
     * @param gearCount The desired gear count
     * @throws BicycleException
     */
    private void setGearCount(int gearCount) throws BicycleException {
        if (gearCount <= 0) {
            throw new BicycleException(BicycleException.INVALID_GEAR_COUNT);
        }
        this.gearCount = gearCount;
    }

    /**
     * The MechanicalBicycle's GearType getter
      * @return The MechanicalBicycle's GearType
     */
    public GearType getGearType() {
        return gearType;
    }

    /**
     * The MechanicalBicycle's GearType setter
      * @param gearType The desired GearType
     * @throws BicycleException gearType cannot be null
     */
    private void setGearType(GearType gearType) throws BicycleException {
        if (gearType == null) {
            throw new BicycleException(BicycleException.NULL_GEAR_TYPE);
        }
        this.gearType = gearType;
    }

    /**
     * The MechanicalBicycle's hasBasket getter
      * @return If the MechanicalBicycle has a basket
     */
    public boolean getHasBasket() {
        return hasBasket;
    }

    /**
     * The MechanicalBicycle's hasBasket setter
      * @param hasBasket if the MechanicalBicycle has a basket
     */
    private void setHasBasket(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
    @Override
    public String toString() {
        return super.toString().replaceAll("typeParams",
                "\"MECHANICAL\",\"mechanical\":{" +
                "\"gearCount\":" + getGearCount() + "," +
                "\"gearType\":\"" + getGearType().getDescription() + "\"," +
                "\"hasBasket\":" + getHasBasket() +
                "}");
    }
}
