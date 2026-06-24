//package org.example;
//
//public interface IMeasurable {
//
//    double getConversionFactor();
//
//    double convertToBaseUnit(double value);
//
//    double convertFromBaseUnit(double baseValue);
//
//    String getUnitName();
//}
package org.example.units;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(
            double value
    );

    double convertFromBaseUnit(
            double baseValue
    );

    String getUnitName();

    // UC14

    SupportsArithmetic supportsArithmetic =
            () -> true;

    default boolean supportsArithmetic() {

        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(
            String operation
    ) {

        // default: allowed
    }

}