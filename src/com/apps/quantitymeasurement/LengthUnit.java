package com.apps.quantitymeasurement;


public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    public double toFeet(double value) {
        return value * toFeet;
    }
}