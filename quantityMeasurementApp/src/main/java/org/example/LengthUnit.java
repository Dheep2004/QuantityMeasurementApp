package org.example;

/**
 * Enum representing supported length units
 * along with their conversion factors relative to feet.
 */
public enum LengthUnit implements IMeasurable {

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
    // Convert current unit → base unit (feet)

    public double convertToBaseUnit(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Invalid numeric value"
            );
        }

        return value * conversionFactor;
    }

    // Convert base unit (feet) → target unit

    public double convertFromBaseUnit(double baseValue) {

        if (!Double.isFinite(baseValue)) {
            throw new IllegalArgumentException(
                    "Invalid numeric value"
            );
        }

        return baseValue / conversionFactor;
    }
    @Override
    public String getUnitName() {

        return this.name();
    }
}