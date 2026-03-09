package edu.uoc.uocycle.model.bicycle;

import java.time.LocalDate;

public class ElectricalBicycle extends Bicycle {
    private final Battery battery;
    private final Motor motor;

    /**
     * Constructor
      * @param id The ElectricalBicycle id
     * @param status The AvailabilityStatus
     * @param weight The ElectricalBicycle's weight
     * @param registrationDate The ElectricalBicycle's registration date
     * @param lastMaintenanceDate The ElectricalBicycle's last maintenance date
     * @param batteryCapacity The Battery's max capacity
     * @param batteryVoltage The Battery's voltage
     * @param batteryCurrent The Battery's current
     * @param motorPower The Motor's power
     * @param motorMaxSpeed The Motor's max speed
     * @param motorHasRegenerativeBraking If the Motor has regenerative braking
     * @throws BicycleException if Bicycle, Battery or Motor constructors throw a BicycleException
     */
    public ElectricalBicycle(String id, AvailabilityStatus status, float weight,
                      LocalDate registrationDate, LocalDate lastMaintenanceDate,
                             int batteryCapacity, int batteryVoltage,
                             float batteryCurrent, int motorPower,
                             float motorMaxSpeed, boolean motorHasRegenerativeBraking)
            throws BicycleException {
        super(id, status, weight, registrationDate, lastMaintenanceDate);
        battery = new Battery(batteryCapacity, batteryVoltage, batteryCurrent);
        motor = new Motor(motorPower, motorMaxSpeed, motorHasRegenerativeBraking);
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
    @Override
    public String toString() {
        return super.toString().replaceAll("typeParams",
                "\"ELECTRIC\",\"battery\":" +
                        battery.toString() + "," +
                        "\"motor\":" +
                        motor.toString());
    }
}