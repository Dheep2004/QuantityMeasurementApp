package org.example.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class QuantityDTO
        implements Serializable {

    @NotNull
    @Positive(message = "Value must be greater than zero")
    private Double value;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotBlank(message = "Measurement Type is required")
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