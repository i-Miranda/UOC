package edu.uoc.uocycle.model.bicycle;

public class Motor {
    private int power;
    private float maxSpeed;
    private boolean hasRegenerativeBraking;

    /**
     * Constructor
      * @param power The Motor's power
     * @param maxSpeed The Motor's max speed
     * @param hasRegenerativeBraking If the Motor has regenerative braking
     * @throws BicycleException If there is an error while setting the data.
     */
    public Motor(int power, float maxSpeed, boolean hasRegenerativeBraking) throws BicycleException {
        setPower(power);
        setMaxSpeed(maxSpeed);
        setHasRegenerativeBraking(hasRegenerativeBraking);
    }

    /**
     * The Motor's power getter
     * @return The Motor's power
     */
    public int getPower() {
        return power;
    }

    /**
     * The Motor's power setter
      * @param power The Motor's power
     * @throws BicycleException If the power is less than or equal to zero.
     */
    private void setPower(int power) throws BicycleException {
        if (power <= 0) {
            throw new BicycleException(BicycleException.INVALID_MOTOR_POWER);
        }
        this.power = power;
    }

    /**
     * The Motor's max speed getter
      * @return The Motor's max speed
     */
    public float getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * The Motor's max speed setter
      * @param maxSpeed The desired max speed
     * @throws BicycleException If the max speed is less than or equal to 0.0f.
     */
    private void setMaxSpeed(float maxSpeed) throws BicycleException {
        if (maxSpeed <= 0.0f) {
            throw new BicycleException(BicycleException.INVALID_MOTOR_MAX_SPEED);
        }
        this.maxSpeed = maxSpeed;
    }

    /**
     * The Motor's regenerative braking getter
      * @return The Motor's regenerative braking
     */
    public boolean getHasRegenerativeBraking() {
        return hasRegenerativeBraking;
    }

    /**
     * The Motor's regenerative braking setter
      * @param hasRegenerativeBraking The desired regenerative braking
     */
    private void setHasRegenerativeBraking(boolean hasRegenerativeBraking) {
        this.hasRegenerativeBraking = hasRegenerativeBraking;
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
    @Override
    public String toString() {
        return "{" +
                "\"power\":" + getPower() + "," +
                "\"maxSpeed\":" + getMaxSpeed() + "," +
                "\"hasRegenerativeBraking\":" + getHasRegenerativeBraking() +
                "}";
    }
}
