package org.example;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

     enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (b == 0) {
                throw new ArithmeticException(
                        "Division by zero is not allowed"
                );
            }

            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(
                DoubleBinaryOperator operation
        ) {
            this.operation = operation;
        }

        public double compute(
                double left,
                double right
        ) {
            return operation.applyAsDouble(
                    left,
                    right
            );
        }
    }

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

    private double roundToTwoDecimals(
            double value
    ) {

        return Math.round(
                value * 100.0
        ) / 100.0;
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        if (
                !unit.getClass()
                        .equals(
                                other.unit.getClass()
                        )
        ) {

            throw new IllegalArgumentException(
                    "Cross-category operations are not allowed"
            );
        }

        if (
                !Double.isFinite(value)
                        ||
                        !Double.isFinite(
                                other.value
                        )
        ) {

            throw new IllegalArgumentException(
                    "Invalid numeric values"
            );
        }

        if (
                targetRequired
                        &&
                        targetUnit == null
        ) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double thisBase =
                unit.convertToBaseUnit(
                        value
                );

        double otherBase =
                other.unit
                        .convertToBaseUnit(
                                other.value
                        );

        return operation.compute(
                thisBase,
                otherBase
        );
    }

    @Override
    public boolean equals(
            Object obj
    ) {

        if (this == obj)
            return true;

        if (
                obj == null
                        ||
                        getClass()
                                != obj.getClass()
        ) {
            return false;
        }

        Quantity<?> other =
                (Quantity<?>) obj;

        if (
                unit.getClass()
                        !=
                        other.unit.getClass()
        ) {

            return false;
        }

        return Math.abs(
                toBaseUnit()
                        -
                        other.toBaseUnit()
        ) < 0.01;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                Math.round(
                        toBaseUnit()
                                * 100
                )
        );
    }

    public Quantity<U> convertTo(
            U targetUnit
    ) {

        if (
                targetUnit == null
        ) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double converted =
                targetUnit
                        .convertFromBaseUnit(
                                toBaseUnit()
                        );

        return new Quantity<>(

                roundToTwoDecimals(
                        converted
                ),

                targetUnit
        );
    }

    public Quantity<U> add(
            Quantity<U> other
    ) {

        return add(
                other,
                unit
        );
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        double result =
                targetUnit
                        .convertFromBaseUnit(
                                resultBase
                        );

        return new Quantity<>(

                roundToTwoDecimals(
                        result
                ),

                targetUnit
        );
    }

    public Quantity<U> subtract(
            Quantity<U> other
    ) {

        return subtract(
                other,
                unit
        );
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        double result =
                targetUnit
                        .convertFromBaseUnit(
                                resultBase
                        );

        return new Quantity<>(

                roundToTwoDecimals(
                        result
                ),

                targetUnit
        );
    }

    public double divide(
            Quantity<U> other
    ) {

        validateArithmeticOperands(
                other,
                null,
                false
        );

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    @Override
    public String toString() {

        return value
                + " "
                + unit.getUnitName();
    }
}