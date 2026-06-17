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

            this.operation =
                    operation;
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

        this.value =
                value;

        this.unit =
                unit;
    }

    public double getValue() {

        return value;
    }

    public U getUnit() {

        return unit;
    }

    private double toBaseUnit() {

        return unit.convertToBaseUnit(
                value
        );
    }

    private double round(
            double value
    ) {

        return Math.round(
                value * 100.0
        ) / 100.0;
    }

    // UC14

    private void validateArithmetic(
            String operation
    ) {

        unit.validateOperationSupport(
                operation
        );
    }

    private void validateOperands(

            Quantity<U> other,

            U target,

            boolean targetRequired
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        if (

                unit.getClass()

                        !=

                        other.unit.getClass()
        ) {

            throw new IllegalArgumentException(

                    "Cross-category operations are not allowed"
            );
        }

        if (

                targetRequired

                        &&

                        target == null
        ) {

            throw new IllegalArgumentException(

                    "Target unit cannot be null"
            );
        }
    }

    private double performArithmetic(

            Quantity<U> other,

            ArithmeticOperation operation
    ) {

        double left =
                this.toBaseUnit();

        double right =
                other.toBaseUnit();

        return operation.compute(
                left,
                right
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
                                !=
                                obj.getClass()
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

                this.toBaseUnit()

                        -

                        other.toBaseUnit()

        ) < 0.01;
    }

    @Override
    public int hashCode() {

        return Objects.hash(

                round(
                        toBaseUnit()
                )
        );
    }

    // UC14 Temperature conversion support

    public Quantity<U> convertTo(
            U targetUnit
    ) {

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double result;

        result =

                targetUnit
                        .convertFromBaseUnit(

                                toBaseUnit()
                        );

        return new Quantity<>(

                round(
                        result
                ),

                targetUnit
        );
    }

    // ADD

    public Quantity<U> add(
            Quantity<U> other
    ) {

        validateArithmetic(
                "addition"
        );

        return add(
                other,
                unit
        );
    }

    public Quantity<U> add(

            Quantity<U> other,

            U target
    ) {

        validateArithmetic(
                "addition"
        );

        validateOperands(
                other,
                target,
                true
        );

        double result =

                performArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        return new Quantity<>(

                round(

                        target
                                .convertFromBaseUnit(
                                        result
                                )
                ),

                target
        );
    }

    // SUBTRACT

    public Quantity<U> subtract(
            Quantity<U> other
    ) {

        validateArithmetic(
                "subtraction"
        );

        return subtract(
                other,
                unit
        );
    }

    public Quantity<U> subtract(

            Quantity<U> other,

            U target
    ) {

        validateArithmetic(
                "subtraction"
        );

        validateOperands(
                other,
                target,
                true
        );

        double result =

                performArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        return new Quantity<>(

                round(

                        target
                                .convertFromBaseUnit(
                                        result
                                )
                ),

                target
        );
    }

    // DIVIDE

    public double divide(
            Quantity<U> other
    ) {

        validateArithmetic(
                "division"
        );

        validateOperands(
                other,
                null,
                false
        );

        return round(

                performArithmetic(

                        other,

                        ArithmeticOperation.DIVIDE
                )
        );
    }

    @Override
    public String toString() {

        return value

                + " "

                + unit.getUnitName();
    }
}