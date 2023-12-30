package com.cargocompass.app.commondata.model.enums;

public enum TransportType {
    TRUCK("Truck", "Suitable for long distances, large cargo capacity.", 30000),
    VAN("Van", "Ideal for small and medium-sized cargos.", 3500),
    CONTAINER("Container", "For international shipping via sea.", 40000),
    FLATBED("Flatbed", "Suitable for heavy and bulky cargos.", 25000),
    OTHER("Other", "For non-standard and special transportation needs.", 0); // Additional options can be added

    private final String displayValue;
    private final String description;
    private final int maxWeight; // Maximum weight in kilograms

    TransportType(String displayValue, String description, int maxWeight) {
        this.displayValue = displayValue;
        this.description = description;
        this.maxWeight = maxWeight;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

}

