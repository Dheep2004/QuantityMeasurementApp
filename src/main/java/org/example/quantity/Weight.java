package org.example.quantity;

// import java.util.Objects;

import org.example.units.WeightUnit;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Weight unit cannot be null"
            );
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Invalid weight value"
            );
        }

        this.value = value;
        this.unit = unit;
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

        Weight other = (Weight) obj;

        return Math.abs(
                this.toBaseUnit() -
                        other.toBaseUnit()
        ) < 0.0001;
    }

    // Optional best practice
//    @Override
//    public int hashCode() {
//        return Objects.hash(toBaseUnit());
//    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(
                convertedValue,
                targetUnit
        );
    }

    private static Weight addInternal(
            Weight w1,
            Weight w2,
            WeightUnit targetUnit
    ) {

        double first =
                w1.toBaseUnit();

        double second =
                w2.toBaseUnit();

        double total =
                first + second;

        double result =
                targetUnit.convertFromBaseUnit(total);

        return new Weight(
                result,
                targetUnit
        );
    }

    // UC6 style
    public Weight add(Weight other) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other weight cannot be null"
            );
        }

        return addInternal(
                this,
                other,
                this.unit
        );
    }

    // UC7 style
    public Weight add(
            Weight other,
            WeightUnit targetUnit
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other weight cannot be null"
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

    // Static overloaded add
    public static Weight add(
            Weight w1,
            Weight w2,
            WeightUnit targetUnit
    ) {

        if (w1 == null || w2 == null) {

            throw new IllegalArgumentException(
                    "Operands cannot be null"
            );
        }

        return addInternal(
                w1,
                w2,
                targetUnit
        );
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {

        return value + " " + unit;
    }
}
