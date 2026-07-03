package org.example.quantity;

// import java.util.Objects;

import org.example.units.LengthUnit;

/**
 * Immutable value object representing a length.
 *
 * Supports:
 * - equality comparison
 * - conversion between units
 * - normalization to common base unit
 */
public class Length {

    private final double value;

    private final LengthUnit unit;

    // Constructor

    public Length(double value, LengthUnit unit) {

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

    /**
     * UC5 OLD LOGIC
     * Converts current value to inches.
     */

//    private double toInches() {
//        return value * unit.getConversionFactor();
//    }

    /**
     * UC8 REFACTORED LOGIC
     * Converts current value to base unit (feet)
     * using LengthUnit responsibility.
     */

    private double toBaseUnit() {

        return unit.convertToBaseUnit(value);
    }

    /**
     * Overridden equals method for value-based comparison.
     */

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null ||
                getClass() != obj.getClass()) {
            return false;
        }

        Length other = (Length) obj;

        // OLD UC5 LOGIC

//        return Math.abs(
//                this.toInches() -
//                        other.toInches()
//        ) < 0.0001;

        // UC8 REFACTORED LOGIC

        return Math.abs(
                this.toBaseUnit() -
                        other.toBaseUnit()
        ) < 0.01;
    }

    // Optional Best Practice

//    @Override
//    public int hashCode() {
//        return Objects.hash(toBaseUnit());
//    }

    /**
     * Static conversion API.
     */

//    public static double convert(
//            double value,
//            LengthUnit source,
//            LengthUnit target
//    ) {
//
//        if (source == null || target == null) {
//            throw new IllegalArgumentException(
//                    "Units cannot be null"
//            );
//        }
//
//        if (!Double.isFinite(value)) {
//            throw new IllegalArgumentException(
//                    "Invalid numeric value"
//            );
//        }
//
//        // source → inches
//
//        double inches =
//                value * source.getConversionFactor();
//
//        // inches → target
//
//        return inches / target.getConversionFactor();
//    }

    /**
     * UC8 Refactored conversion logic.
     */

    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target
    ) {

        if (source == null || target == null) {

            throw new IllegalArgumentException(
                    "Units cannot be null"
            );
        }

        if (!Double.isFinite(value)) {

            throw new IllegalArgumentException(
                    "Invalid numeric value"
            );
        }

        // source → base unit

        double baseValue =
                source.convertToBaseUnit(value);

        // base unit → target

        return target.convertFromBaseUnit(baseValue);
    }

    /**
     * Instance conversion method.
     * Returns NEW immutable Length object.
     */

    // OLD UC5 LOGIC

//    public Length convertTo(LengthUnit targetUnit) {
//
//        if (targetUnit == null) {
//            throw new IllegalArgumentException(
//                    "Units cannot be null"
//            );
//        }
//
//        double convertedValue =
//                convert(
//                        this.value,
//                        this.unit,
//                        targetUnit
//                );
//
//        return new Length(
//                convertedValue,
//                targetUnit
//        );
//    }

    /**
     * UC8 Refactored conversion method.
     */

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        // Convert source → base unit

        double baseValue =
                unit.convertToBaseUnit(value);

        // Convert base → target unit

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Length(
                convertedValue,
                targetUnit
        );
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {

        return value + " " + unit;
    }

    /**
     * UC6 OLD ADDITION LOGIC
     */

//    public Length add(Length other) {
//
//        if (other == null) {
//
//            throw new IllegalArgumentException(
//                    "Second operand cannot be null"
//            );
//        }
//
//        // Convert both to inches
//
//        double thisInches = this.toInches();
//
//        double otherInches = other.toInches();
//
//        // Add
//
//        double totalInches =
//                thisInches + otherInches();
//
//        // Convert back to current unit
//
//        double resultValue =
//                totalInches /
//                        unit.getConversionFactor();
//
//        return new Length(
//                resultValue,
//                this.unit
//        );
//    }

    /**
     * UC8 Refactored internal addition logic.
     */

    private static Length addInternal(
            Length l1,
            Length l2,
            LengthUnit targetUnit
    ) {

        // Convert both → base unit

        double first =
                l1.unit.convertToBaseUnit(l1.value);

        double second =
                l2.unit.convertToBaseUnit(l2.value);

        // Add

        double total = first + second;

        // Convert result → target unit

        double result =
                targetUnit.convertFromBaseUnit(total);

        return new Length(
                result,
                targetUnit
        );
    }

    /**
     * UC6 Addition
     * Result in current object's unit.
     */

    public Length add(Length other) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Second operand cannot be null"
            );
        }

        return addInternal(
                this,
                other,
                this.unit
        );
    }

    /**
     * UC7 Addition with explicit target unit.
     */

    public Length add(
            Length other,
            LengthUnit targetUnit
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Second operand cannot be null"
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

    /**
     * Static overloaded add method.
     */

    public static Length add(
            Length l1,
            Length l2,
            LengthUnit targetUnit
    ) {

        if (l1 == null || l2 == null) {

            throw new IllegalArgumentException(
                    "Operands cannot be null"
            );
        }

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        return addInternal(
                l1,
                l2,
                targetUnit
        );
    }

    /**
     * Overloaded add method using raw values.
     */

    public static Length add(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2,
            LengthUnit targetUnit
    ) {

        Length l1 =
                new Length(value1, unit1);

        Length l2 =
                new Length(value2, unit2);

        return add(
                l1,
                l2,
                targetUnit
        );
    }
}