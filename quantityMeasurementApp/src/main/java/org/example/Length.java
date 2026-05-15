package org.example;

// import java.util.Objects;

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
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        this.value = value;
        this.unit = unit;
    }

    /**
     * Converts current value to base unit (inches).
     */
    private double toInches() {
        return value * unit.getConversionFactor();
    }

    /**
     * Overridden equals method for value-based comparison.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Math.abs(
                this.toInches() -
                        other.toInches()
        ) < 0.0001;
    }

    // Best practice extension
    // @Override
    // public int hashCode() {
    //     return Objects.hash(toInches());
    // }

    /**
     * Static conversion API.
     */
    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target
    ) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        // source → inches
        double inches =
                value * source.getConversionFactor();

        // inches → target
        return inches / target.getConversionFactor();
    }

    /**
     * Instance conversion method.
     * Returns NEW immutable Length object.
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double convertedValue =
                convert(this.value,
                        this.unit,
                        targetUnit);

        return new Length(convertedValue, targetUnit);
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
     * Adds another Length object.
     * Result unit = current object's unit.
     */
//    public Length add(Length other) {
//
//        if (other == null) {
//            throw new IllegalArgumentException(
//                    "Second operand cannot be null"
//            );
//        }
//
//        // Convert both to inches
//        double thisInches = this.toInches();
//
//        double otherInches = other.toInches();
//
//        // Add
//        double totalInches =
//                thisInches + otherInches;
//
//        // Convert back to current unit
//        double resultValue =
//                totalInches /
//                        unit.getConversionFactor();
//
//        return new Length(resultValue, this.unit);
//    }

    private static Length addInternal(
            Length l1,
            Length l2,
            LengthUnit targetUnit
    ) {

        double first = l1.toInches();

        double second = l2.toInches();

        double total = first + second;

        double result =
                total / targetUnit.getConversionFactor();

        return new Length(result, targetUnit);
    }
    public Length add(Length other) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Second operand cannot be null"
            );
        }

        return addInternal(this, other, this.unit);
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

        return addInternal(this, other, targetUnit);
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

        return addInternal(l1, l2, targetUnit);
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

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return add(l1, l2, targetUnit);

    }

}