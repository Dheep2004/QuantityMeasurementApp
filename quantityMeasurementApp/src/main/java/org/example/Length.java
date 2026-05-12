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
}