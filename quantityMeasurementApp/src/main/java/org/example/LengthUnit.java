package org.example;

/**
 * Enum representing supported length units
 * along with their conversion factors relative to feet.
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Returns conversion factor relative to feet.
     */
    public double getConversionFactor() {
        return conversionFactor;
    }
}