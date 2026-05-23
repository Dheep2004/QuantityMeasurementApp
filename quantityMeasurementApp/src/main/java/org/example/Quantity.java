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
}