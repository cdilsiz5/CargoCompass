package com.transmarket.app.cargoservice.model.enums;

public enum CargoStatus {
    AWAITING_PICKUP("Awaiting Pickup", "Waiting to be picked up by the carrier."),
    ASSIGNED_TO_CARRIER("Assigned to Carrier", "Assigned to a carrier and waiting to depart."),
    IN_TRANSIT("In Transit", "Cargo is on the way to the destination."),
    DELIVERED("Delivered", "Cargo has reached its destination and is delivered."),
    CANCELLED("Cancelled", "Cargo has been cancelled or the transportation process has been terminated.");

    private final String displayValue;
    private final String description;

    CargoStatus(String displayValue, String description) {
        this.displayValue = displayValue;
        this.description = description;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getDescription() {
        return description;
    }
}
