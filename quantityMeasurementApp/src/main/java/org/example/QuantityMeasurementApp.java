package org.example;

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
                " UC3 Equality Demonstrations "
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.FEET,
                1.0,
                LengthUnit.FEET
        );

        System.out.println(
                "\n UC4 Extended Unit Demonstrations "
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.YARDS,
                36.0,
                LengthUnit.INCHES
        );

        demonstrateLengthEquality(
                2.54,
                LengthUnit.CENTIMETERS,
                1.0,
                LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );

        System.out.println(
                "\nUC5 Conversion Demonstrations"
        );

        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        demonstrateLengthConversion(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        Length yard =
                new Length(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES
        );

        System.out.println(
                "\nUC6 Addition Demonstration "
        );

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                2.0,
                LengthUnit.FEET,
                LengthUnit.FEET
        );

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        demonstrateLengthAddition(
                12.0,
                LengthUnit.INCHES,
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthAddition(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET,
                LengthUnit.YARDS
        );

        demonstrateLengthAddition(
                2.54,
                LengthUnit.CENTIMETERS,
                1.0,
                LengthUnit.INCHES,
                LengthUnit.CENTIMETERS
        );

        System.out.println(
                "\n----------- ALL UC TESTS EXECUTED SUCCESSFULLY -----------"
        );

        // ---------------- UC7 ----------------

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.INCHES
        );

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthAddition(
                1.0,
                LengthUnit.INCHES,
                1.0,
                LengthUnit.INCHES,
                LengthUnit.CENTIMETERS
        );

        // ---------------- UC8 ----------------

        demonstrateBaseUnitConversion();

        // ---------------- UC9 ----------------

        System.out.println(
                "\n=========== UC9 WEIGHT DEMONSTRATION ==========="
        );

        demonstrateWeightEquality(
                1.0,
                WeightUnit.KILOGRAM,
                1000.0,
                WeightUnit.GRAM
        );

        demonstrateWeightConversion(
                1.0,
                WeightUnit.KILOGRAM,
                WeightUnit.POUND
        );

        demonstrateWeightAddition(
                1.0,
                WeightUnit.KILOGRAM,
                1000.0,
                WeightUnit.GRAM,
                WeightUnit.KILOGRAM
        );

        demonstrateWeightAddition(
                1.0,
                WeightUnit.POUND,
                453.592,
                WeightUnit.GRAM,
                WeightUnit.POUND
        );

        // ---------------- UC10 ----------------

        System.out.println(
                "\n----------- UC10 Generic Quantity Demonstration -----------"
        );

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(
                length1,
                LengthUnit.INCHES
        );

        demonstrateAddition(
                length1,
                length2,
                LengthUnit.FEET
        );

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(
                weight1,
                WeightUnit.GRAM
        );

        demonstrateAddition(
                weight1,
                weight2,
                WeightUnit.KILOGRAM
        );

        System.out.println(
                "\n----------- UC10 Executed Successfully -----------"
        );
        // ---------------- UC11 VOLUME DEMONSTRATION ----------------

        System.out.println(
                "\n=========== UC11 VOLUME DEMONSTRATION ==========="
        );

// Equality

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                );

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                );

        demonstrateEquality(
                volume1,
                volume2
        );

// Conversion

        demonstrateConversion(
                volume1,
                VolumeUnit.MILLILITRE
        );

        demonstrateConversion(
                volume3,
                VolumeUnit.LITRE
        );

// Addition

        demonstrateAddition(
                volume1,
                volume2,
                VolumeUnit.LITRE
        );

        demonstrateAddition(
                volume1,
                volume3,
                VolumeUnit.GALLON
        );

        System.out.println(
                "\n=========== UC11 EXECUTED SUCCESSFULLY ==========="
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
}