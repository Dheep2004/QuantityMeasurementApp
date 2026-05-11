package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        double thisInFeet =
                this.value * this.unit.getConversionFactor();

        double otherInFeet =
                other.value * other.unit.getConversionFactor();

        return Math.abs(thisInFeet - otherInFeet) < 0.0001;
    }
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        // convert source → feet
        double valueInFeet =
                value * source.getConversionFactor();

        // feet → target
        return valueInFeet / target.getConversionFactor();
    }
}