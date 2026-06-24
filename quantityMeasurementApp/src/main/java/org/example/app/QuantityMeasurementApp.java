package org.example.app;

import org.example.units.IMeasurable;
import org.example.quantity.Length;
import org.example.quantity.Quantity;
import org.example.quantity.Weight;
import org.example.units.LengthUnit;
import org.example.units.TemperatureUnit;
import org.example.units.VolumeUnit;
import org.example.units.WeightUnit;

public class QuantityMeasurementApp {

    // ---------------- UC1 + UC2 Legacy Code ----------------

//    public static class Feet {
//        private final double value;
//
//        public Feet(double value) {
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//
//            if (this == obj) return true;
//
//            if (obj == null) return false;
//
//            if (this.getClass() != obj.getClass()) return false;
//
//            Feet other = (Feet) obj;
//
//            return Double.compare(this.value, other.value) == 0;
//        }
//    }
//
//    public static class Inches {
//
//        private final double value;
//
//        public Inches(double value) {
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//
//            if (this == obj) return true;
//
//            if (obj == null) return false;
//
//            if (this.getClass() != obj.getClass()) return false;
//
//            Inches other = (Inches) obj;
//
//            return Double.compare(this.value, other.value) == 0;
//        }
//    }

    // ---------------- UC3 Equality ----------------

    public static void demonstrateLengthEquality(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2
    ) {

        Length length1 =
                new Length(value1, unit1);

        Length length2 =
                new Length(value2, unit2);

        System.out.println(
                value1 + " " + unit1 +
                        " and " +
                        value2 + " " + unit2 +
                        " are equal: " +
                        length1.equals(length2)
        );
    }

    // ---------------- UC4 Comparison ----------------

    public static void demonstrateLengthComparison(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2
    ) {

        demonstrateLengthEquality(
                value1,
                unit1,
                value2,
                unit2
        );
    }

    // ---------------- UC5 Conversion ----------------

    public static Length demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit
    ) {

        Length originalLength =
                new Length(value, fromUnit);

        Length convertedLength =
                originalLength.convertTo(toUnit);

        System.out.println(
                "Converted " +
                        value + " " + fromUnit +
                        " to " +
                        convertedLength.getValue() +
                        " " +
                        convertedLength.getUnit()
        );

        return convertedLength;
    }

    // Overloaded conversion method

    public static Length demonstrateLengthConversion(
            Length length,
            LengthUnit toUnit
    ) {

        Length convertedLength =
                length.convertTo(toUnit);

        System.out.println(
                "Converted " +
                        length +
                        " to " +
                        convertedLength
        );

        return convertedLength;
    }

    // ---------------- UC6 Addition ----------------

    public static Length demonstrateLengthAddition(
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

        double first =
                l1.getValue() *
                        l1.getUnit().getConversionFactor();

        double second =
                l2.getValue() *
                        l2.getUnit().getConversionFactor();

        double total = first + second;

        double result =
                total /
                        targetUnit.getConversionFactor();

        Length resultLength =
                new Length(result, targetUnit);

        System.out.println(
                l1 + " + " + l2 +
                        " = " + resultLength
        );

        return resultLength;
    }

    // Overloaded addition method

    public static Length demonstrateLengthAddition(
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

        return demonstrateLengthAddition(
                l1,
                l2,
                targetUnit
        );
    }

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {

        System.out.println(
                "\n===== QUANTITY MEASUREMENT UC15 =====\n"
        );

        // ---------------- LENGTH ----------------

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12,
                        LengthUnit.INCHES
                );

        demonstrateEquality(
                feet,
                inches
        );

        demonstrateConversion(
                feet,
                LengthUnit.INCHES
        );

        demonstrateAddition(
                feet,
                inches,
                LengthUnit.FEET
        );

        // ---------------- WEIGHT ----------------

        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000,
                        WeightUnit.GRAM
                );

        demonstrateEquality(
                kilogram,
                gram
        );

        demonstrateConversion(
                kilogram,
                WeightUnit.GRAM
        );

        demonstrateAddition(
                kilogram,
                gram,
                WeightUnit.KILOGRAM
        );

        // ---------------- VOLUME ----------------

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> milliLitre =
                new Quantity<>(
                        1000,
                        VolumeUnit.MILLILITRE
                );

        demonstrateEquality(
                litre,
                milliLitre
        );

        demonstrateConversion(
                litre,
                VolumeUnit.MILLILITRE
        );

        demonstrateAddition(
                litre,
                milliLitre,
                VolumeUnit.LITRE
        );

        // ---------------- SUBTRACTION ----------------

        demonstrateSubtraction();

        // ---------------- DIVISION ----------------

        demonstrateDivision();

        // ---------------- TEMPERATURE ----------------

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(
                        0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(
                        32,
                        TemperatureUnit.FAHRENHEIT
                );

        demonstrateEquality(
                celsius,
                fahrenheit
        );

        demonstrateConversion(
                celsius,
                TemperatureUnit.FAHRENHEIT
        );

        Quantity<TemperatureUnit> kelvin =
                new Quantity<>(
                        273.15,
                        TemperatureUnit.KELVIN
                );

        demonstrateEquality(
                celsius,
                kelvin
        );

        try {

            celsius.add(
                    fahrenheit
            );

        }

        catch (
                UnsupportedOperationException e
        ) {

            System.out.println(
                    e.getMessage()
            );
        }

        System.out.println(
                "\n===== UC15 EXECUTED SUCCESSFULLY ====="
        );
    }

    // ---------------- UC8 Base Unit Demonstration ----------------

    public static void demonstrateBaseUnitConversion() {

        System.out.println(
                "\n----------- UC8 Base Unit Conversion -----------"
        );

        System.out.println(
                "12 Inches to Feet = " +
                        LengthUnit.INCHES.convertToBaseUnit(12.0)
        );

        System.out.println(
                "1 Yard to Feet = " +
                        LengthUnit.YARDS.convertToBaseUnit(1.0)
        );

        System.out.println(
                "30.48 CM to Feet = " +
                        LengthUnit.CENTIMETERS.convertToBaseUnit(30.48)
        );
    }

    // ---------------- UC9 WEIGHT EQUALITY ----------------

    public static void demonstrateWeightEquality(
            double value1,
            WeightUnit unit1,
            double value2,
            WeightUnit unit2
    ) {

        Weight w1 =
                new Weight(value1, unit1);

        Weight w2 =
                new Weight(value2, unit2);

        System.out.println(
                w1 + " and " +
                        w2 +
                        " are equal : " +
                        w1.equals(w2)
        );
    }

    // ---------------- UC9 WEIGHT CONVERSION ----------------

    public static Weight demonstrateWeightConversion(
            double value,
            WeightUnit fromUnit,
            WeightUnit toUnit
    ) {

        Weight original =
                new Weight(value, fromUnit);

        Weight converted =
                original.convertTo(toUnit);

        System.out.println(
                "Converted " +
                        original +
                        " to " +
                        converted
        );

        return converted;
    }

    // ---------------- UC9 WEIGHT ADDITION ----------------

    public static Weight demonstrateWeightAddition(
            double value1,
            WeightUnit unit1,
            double value2,
            WeightUnit unit2,
            WeightUnit targetUnit
    ) {

        Weight w1 =
                new Weight(value1, unit1);

        Weight w2 =
                new Weight(value2, unit2);

        Weight result =
                w1.add(w2, targetUnit);

        System.out.println(
                w1 + " + " +
                        w2 +
                        " = " +
                        result
        );

        return result;
    }

    // ---------------- UC10 GENERIC METHODS ----------------

    public static <U extends IMeasurable>
    void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2
    ) {

        System.out.println(
                q1 + " equals " +
                        q2 + " : " +
                        q1.equals(q2)
        );
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit
    ) {

        System.out.println(
                quantity + " converted to " +
                        quantity.convertTo(targetUnit)
        );
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        System.out.println(
                q1 + " + " +
                        q2 + " = " +
                        q1.add(q2, targetUnit)
        );
    }
    public static void demonstrateSubtraction() {

        System.out.println("========== SUBTRACTION OPERATIONS ==========\n");

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result1 = feet.subtract(inches);

        System.out.println(
                feet.getValue() + " " + feet.getUnit()
                        + " - "
                        + inches.getValue() + " " + inches.getUnit()
                        + " = "
                        + result1.getValue() + " " + result1.getUnit()
        );

        Quantity<LengthUnit> result2 =
                feet.subtract(inches, LengthUnit.INCHES);

        System.out.println(
                feet.getValue() + " " + feet.getUnit()
                        + " - "
                        + inches.getValue() + " " + inches.getUnit()
                        + " = "
                        + result2.getValue() + " " + result2.getUnit()
        );

        Quantity<WeightUnit> kilogram =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result3 =
                kilogram.subtract(gram);

        System.out.println(
                kilogram.getValue() + " " + kilogram.getUnit()
                        + " - "
                        + gram.getValue() + " " + gram.getUnit()
                        + " = "
                        + result3.getValue() + " " + result3.getUnit()
        );

        Quantity<VolumeUnit> litre =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> milliLitre =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result4 =
                litre.subtract(milliLitre);

        System.out.println(
                litre.getValue() + " " + litre.getUnit()
                        + " - "
                        + milliLitre.getValue() + " " + milliLitre.getUnit()
                        + " = "
                        + result4.getValue() + " " + result4.getUnit()
        );
    }
    public static void demonstrateDivision() {

        System.out.println("\n========== DIVISION OPERATIONS ==========\n");

        Quantity<LengthUnit> feet1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> feet2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        double result1 = feet1.divide(feet2);

        System.out.println(
                feet1.getValue() + " " + feet1.getUnit()
                        + " ÷ "
                        + feet2.getValue() + " " + feet2.getUnit()
                        + " = "
                        + result1
        );

        Quantity<LengthUnit> inches =
                new Quantity<>(24.0, LengthUnit.INCHES);

        double result2 = inches.divide(feet2);

        System.out.println(
                inches.getValue() + " " + inches.getUnit()
                        + " ÷ "
                        + feet2.getValue() + " " + feet2.getUnit()
                        + " = "
                        + result2
        );

        Quantity<WeightUnit> kilogram =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> kilogram2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        double result3 = kilogram.divide(kilogram2);

        System.out.println(
                kilogram.getValue() + " " + kilogram.getUnit()
                        + " ÷ "
                        + kilogram2.getValue() + " " + kilogram2.getUnit()
                        + " = "
                        + result3
        );

        Quantity<VolumeUnit> litre =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> litre2 =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        double result4 = litre.divide(litre2);

        System.out.println(
                litre.getValue() + " " + litre.getUnit()
                        + " ÷ "
                        + litre2.getValue() + " " + litre2.getUnit()
                        + " = "
                        + result4
        );
    }

}