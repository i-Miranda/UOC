package edu.uoc.uocycle.model.bicycle;

import java.time.LocalDate;

public abstract class Bicycle {
    private String id;
    private AvailabilityStatus status;
    private float weight;
    private LocalDate registrationDate;
    private LocalDate lastMaintenanceDate;

    /**
     * Constructor
     * @param id The Bicycle's unique ID.
     * @param status The AvailabilityStatus.
     * @param weight The Bicycle's weight.
     * @param registrationDate The date when Bicycle was registered.
     * @param lastMaintenanceDate The Bicycle's last maintenance date.
     * @throws BicycleException If an error occurs while setting the variables.
     */
    protected Bicycle(String id, AvailabilityStatus status, float weight,
                      LocalDate registrationDate, LocalDate lastMaintenanceDate) throws BicycleException {
        setId(id);
        setStatus(status);
        setWeight(weight);
        setRegistrationDate(registrationDate);
        setLastMaintenanceDate(lastMaintenanceDate);
    }

    /**
     *  Bicycle id getter.
      * @return The Bicycle id
     */
    public String getId() {
        return id;
    }

    /**
     * Bicycle id setter.
      * @param id The Bicycle's unique id.
     * @throws BicycleException If the id is null empty, or blank.
     */
    private void setId(String id) throws BicycleException {
        if (id == null || id.isBlank()) {
            throw new BicycleException(BicycleException.INVALID_ID);
        }
        this.id = id;
    }

    /**
     * AvailabilityStatus getter.
     * @return The Bicycle's AvailabilityStatus.
     */
    public AvailabilityStatus getStatus() {
        return status;
    }

    /**
     * AvailabilityStatus setter.
      * @param status The desired AvailabilityStatus to be set.
     * @throws BicycleException status cannot be null.
     */
    private void setStatus(AvailabilityStatus status) throws BicycleException {
        if (status == null) {
            throw new BicycleException(BicycleException.INVALID_AVAILABILITY_STATUS);
        }
        this.status = status;
    }

    /**
     * The weight getter.
      * @return The Bicycle's weight.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * The weight setter.
      * @param weight The Bicycle's weight.
     * @throws BicycleException Weight cannot be less than or equal to 0.0f.
     */
    private void setWeight(float weight) throws BicycleException {
        if (weight <= 0) {
            throw new BicycleException(BicycleException.INVALID_WEIGHT);
        }
        this.weight = weight;
    }

    /**
     * The registration date getter
      * @return The Bicycle's registration date.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * The registration date setter
      * @param registrationDate The desired registration date.
     * @throws BicycleException Date cannot be null or after current date.
     */
    private void setRegistrationDate(LocalDate registrationDate) throws BicycleException {
        if (registrationDate == null) {
            throw new BicycleException(BicycleException.NULL_REGISTRATION_DATE);
        } else if (registrationDate.isAfter(LocalDate.now())) {
            throw new BicycleException(BicycleException.INVALID_REGISTRATION_DATE);
        }
        this.registrationDate = registrationDate;
    }

    /**
     * The last maintenance date getter.
      * @return The Bicycle's last maintenance date.
     */
    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    /**
     * The last maintenance date setter.
      * @param lastMaintenanceDate The desired last maintenance date.
     * @throws BicycleException The last maintenance date cannot be before registrationDate or after current date.
     */
    private void setLastMaintenanceDate(LocalDate lastMaintenanceDate) throws BicycleException {
        if (lastMaintenanceDate == null) {
            this.lastMaintenanceDate = getRegistrationDate();
            return;
        } else if (lastMaintenanceDate.isAfter(LocalDate.now()) || lastMaintenanceDate.isBefore(getRegistrationDate())) {
            throw new BicycleException(BicycleException.INVALID_MAINTENANCE_DATE);
        }
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    /**
     * The Overridden toString() function
     * @return JSON Stringified Bicycle
     */
   @Override
    public String toString() {
       return "{" +
               "\"id\":\"" + getId() + "\"," +
               "\"status\":\"" + getStatus().getDescription() + "\"," +
               "\"weight\":" + getWeight() + "," +
               "\"registrationDate\":\"" + getRegistrationDate().toString() + "\"," +
               "\"lastMaintenanceDate\":\"" + getLastMaintenanceDate().toString() + "\"," +
               "\"type\":" + "typeParams" +
               "}";
    }
}
