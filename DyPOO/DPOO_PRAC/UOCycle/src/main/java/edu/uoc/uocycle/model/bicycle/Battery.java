package edu.uoc.uocycle.model.bicycle;

public class Battery {
    private int capacity;
    private int voltage;
    private float current;

    /**
     * Constructor
      * @param capacity The Battery's max capacity
     * @param voltage The Battery's voltage
     * @param current The Battery's current
     * @throws BicycleException If error occurs while setting data.
     */
    public Battery(int capacity, int voltage, float current) throws BicycleException {
        setCapacity(capacity);
        setVoltage(voltage);
        setCurrent(current);
    }

    /**
     * The Battery's capacity getter
     * @return The Battery's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * The Battery's capacity setter
      * @param capacity The Battery's max capacity
     * @throws BicycleException If the capacity is less than or equal to zero.
     */
    private void setCapacity(int capacity) throws BicycleException {
        if (capacity <= 0) {
            throw new BicycleException(BicycleException.INVALID_BATTERY_CAPACITY);
        }
        this.capacity = capacity;
    }

    /**
     * The Battery's voltage getter
      * @return The Battery's voltage
     */
    public int getVoltage() {
        return voltage;
    }

    /**
     * The Battery's voltage setter
      * @param voltage The Battery's voltage
     * @throws BicycleException If the voltage is less than or equal to zero.
     */
    private void setVoltage(int voltage) throws BicycleException {
        if (voltage <= 0) {
            throw new BicycleException(BicycleException.INVALID_BATTERY_VOLTAGE);
        }
        this.voltage = voltage;
    }

    /**
     * The Battery's current getter
      * @return The Battery's current
     */
    public float getCurrent() {
        return current;
    }

    /**
     * The Battery's current setter
      * @param current The desired current
     * @throws BicycleException If the current is less than or equal to 0.0f.
     */
    private void setCurrent(float current) throws BicycleException {
        if (current <= 0.0f) {
            throw new BicycleException(BicycleException.INVALID_BATTERY_CURRENT);
        }
        this.current = current;
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified ElectricalBicycle
     */
    @Override
    public String toString() {
        return "{" +
                "\"capacityWh\":" + getCapacity() + "," +
                "\"voltageV\":" + getVoltage() + "," +
                "\"currentAh\":" + getCurrent() +
        "}";
    }
}
