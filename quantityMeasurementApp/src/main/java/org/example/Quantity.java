package org.example;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;

    private final U unit;

    public Quantity(
            double value,
            U unit
    ) {

        if (unit == null) {

            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        if (!Double.isFinite(value)) {

            throw new IllegalArgumentException(
                    "Invalid numeric value"
            );
        }

        this.value = value;

        this.unit = unit;
    }

    public double getValue() {

        return value;
    }

    public U getUnit() {

        return unit;
    }

    private double toBaseUnit() {

        return unit.convertToBaseUnit(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null ||
                getClass() != obj.getClass()) {
            return false;
        }

        Quantity<?> other =
                (Quantity<?>) obj;

        // UC10 runtime category safety

        if (
                this.unit.getClass() !=
                        other.unit.getClass()
        ) {
            return false;
        }

        return Math.abs(
                this.toBaseUnit() -
                        other.toBaseUnit()
        ) < 0.01;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                Math.round(toBaseUnit() * 100)
        );
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        // rounded to 2 decimals

        convertedValue =
                Math.round(convertedValue * 100.0)
                        / 100.0;

        return new Quantity<>(
                convertedValue,
                targetUnit
        );
    }

    private static <
            U extends IMeasurable
            > Quantity<U> addInternal(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        double first =
                q1.toBaseUnit();

        double second =
                q2.toBaseUnit();

        double total =
                first + second;

        double result =
                targetUnit.convertFromBaseUnit(total);

        result =
                Math.round(result * 100.0)
                        / 100.0;

        return new Quantity<>(
                result,
                targetUnit
        );
    }

    // UC6 style

    public Quantity<U> add(
            Quantity<U> other
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

        return addInternal(
                this,
                other,
                this.unit
        );
    }

    // UC7 style

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        return addInternal(
                this,
                other,
                targetUnit
        );
    }

    @Override
    public String toString() {

        return value + " " +
                unit.getUnitName();
    }
    private void validateQuantity(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (this.unit == null || other.unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operations are not allowed");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)
                || Double.isNaN(other.value) || Double.isInfinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric values");
        }
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private double toBaseValue() {
        return unit.convertToBaseUnit(value);
    }
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateQuantity(other);

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double thisBase = this.toBaseValue();
        double otherBase = other.toBaseValue();

        double resultBase = thisBase - otherBase;

        double convertedResult =
                targetUnit.convertFromBaseUnit(resultBase);

        convertedResult = roundToTwoDecimals(convertedResult);

        return new Quantity<>(convertedResult, targetUnit);
    }
    public double divide(Quantity<U> other) {

        validateQuantity(other);

        double thisBase = this.toBaseValue();
        double otherBase = other.toBaseValue();

        if (otherBase == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }

        return thisBase / otherBase;
    }
}