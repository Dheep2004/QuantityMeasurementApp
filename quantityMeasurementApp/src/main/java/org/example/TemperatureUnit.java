package org.example;

import java.util.function.Function;

public enum TemperatureUnit
        implements IMeasurable {

    CELSIUS(

            c -> c,

            c -> c

    ),

    FAHRENHEIT(

            c -> (c * 9 / 5) + 32,

            f -> (f - 32) * 5 / 9

    ),

    KELVIN(

            c -> c + 273.15,

            k -> k - 273.15

    );

    private final Function<Double, Double>
            fromBase;

    private final Function<Double, Double>
            toBase;

    TemperatureUnit(

            Function<Double, Double> fromBase,

            Function<Double, Double> toBase
    ) {

        this.fromBase =
                fromBase;

        this.toBase =
                toBase;
    }

    SupportsArithmetic supportsArithmetic =
            () -> false;

    @Override
    public boolean supportsArithmetic() {

        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(
            String operation
    ) {

        throw new UnsupportedOperationException(

                "Temperature does not support "
                        + operation
        );
    }

    @Override
    public double getConversionFactor() {

        return 1;
    }

    @Override
    public double convertToBaseUnit(
            double value
    ) {

        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(
            double value
    ) {

        return fromBase.apply(value);
    }

    @Override
    public String getUnitName() {

        return name();
    }
}