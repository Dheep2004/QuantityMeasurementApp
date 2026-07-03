package org.example.model;

public class QuantityModel {

    private final double value;

    private final String unit;

    public QuantityModel(
            double value,
            String unit
    ) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}