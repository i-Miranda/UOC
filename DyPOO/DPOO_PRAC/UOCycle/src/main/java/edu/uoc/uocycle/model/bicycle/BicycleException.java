package edu.uoc.uocycle.model.bicycle;

public class BicycleException extends Exception {
    public static final String INVALID_ID = "Bicycle id cannot be null, empty" +
            " or blank.";
    public static final String INVALID_AVAILABILITY_STATUS =
            "AvailabilityStatus cannot be null.";
    public static final String INVALID_WEIGHT = "Weight cannot be less than " +
            "or equal to 0.";
    public static final String NULL_REGISTRATION_DATE = "RegistrationDate " +
            "cannot be null.";
    public static final String INVALID_REGISTRATION_DATE = "RegistrationDate cannot be after current date.";
    public static final String INVALID_MAINTENANCE_DATE ="LastMaintenanceDate" +
            " cannot be after the current date and must be equal to or after RegistrationDate.";
    public static final String INVALID_GEAR_COUNT = "GearCount cannot be less" +
            " than or equal to 0.";
    public static final String NULL_GEAR_TYPE = "GearType cannot be null.";
    public static final String INVALID_BATTERY_CAPACITY = "BatteryCapacity " +
            "cannot be less than or equal to 0.";
    public static final String INVALID_BATTERY_VOLTAGE = "BatteryVoltage " +
            "cannot be less than or equal to 0.";
    public static final String INVALID_BATTERY_CURRENT = "BatteryCurrent " +
            "cannot be less than or equal to 0.0f.";
    public static final String INVALID_MOTOR_POWER = "MotorPower " +
            "cannot be less than or equal to 0.";
    public static final String INVALID_MOTOR_MAX_SPEED = "MotorMaxSpeed " +
            "cannot be less than or equal to 0.0f.";
    public BicycleException(String message) {
        super(message);
    }
}