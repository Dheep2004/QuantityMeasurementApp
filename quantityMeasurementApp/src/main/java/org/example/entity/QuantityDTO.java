package org.example.entity;

import java.io.Serializable;

public class QuantityDTO
        implements Serializable {

    private double value;

    private String unit;

    private String measurementType;

    private boolean hasError;

    private String errorMessage;

    public QuantityDTO(
            double value,
            String unit,
            String measurementType
    ) {

        this.value = value;

        this.unit = unit;

        this.measurementType =
                measurementType;
    }

    public QuantityDTO(
            String errorMessage
    ) {

        this.hasError = true;

        this.errorMessage =
                errorMessage;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {

        if (hasError) {

            return "Error: "
                    + errorMessage;
        }

        return value
                + " "
                + unit;
    }
}