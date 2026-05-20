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

        Length length1 = new Length(value1, unit1);

        Length length2 = new Length(value2, unit2);

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

        // Convert operands to inches
        double first =
                l1.getValue() *
                        l1.getUnit().getConversionFactor();

        double second =
                l2.getValue() *
                        l2.getUnit().getConversionFactor();

        // Add
        double total = first + second;

        // Convert to target unit
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

        // Yard and Feet equality
        demonstrateLengthEquality(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET
        );

        // Yard and Inches equality
        demonstrateLengthEquality(
                1.0,
                LengthUnit.YARDS,
                36.0,
                LengthUnit.INCHES
        );

        // Centimeter and Inches equality
        demonstrateLengthEquality(
                2.54,
                LengthUnit.CENTIMETERS,
                1.0,
                LengthUnit.INCHES
        );

        // Comparison demonstrations
        demonstrateLengthComparison(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );


        System.out.println(
                "\nUC5 Conversion Demonstrations"
        );

        // Feet → Inches
        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        // Inches → Feet
        demonstrateLengthConversion(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        // Yard → Inches
        demonstrateLengthConversion(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES
        );

        // Inches → Yard
        demonstrateLengthConversion(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        // Centimeters → Inches
        demonstrateLengthConversion(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        // Overloaded conversion method
        Length yard =
                new Length(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES
        );



        System.out.println(
                "\nUC6 Addition Demonstration "
        );

        // Test Case 1
        System.out.println("\nTest 1: Feet + Feet");

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                2.0,
                LengthUnit.FEET,
                LengthUnit.FEET
        );

        // Test Case 2
        System.out.println("\nTest 2: Feet + Inches");

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        // Test Case 3
        System.out.println("\nTest 3: Inches + Feet");

        demonstrateLengthAddition(
                12.0,
                LengthUnit.INCHES,
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        // Test Case 4
        System.out.println("\nTest 4: Yard + Feet");

        demonstrateLengthAddition(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET,
                LengthUnit.YARDS
        );

        // Test Case 5
        System.out.println("\nTest 5: Centimeters + Inches");

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
      //uc7

// FEET target

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

// INCHES target

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.INCHES
        );
        // YARDS target

        demonstrateLengthAddition(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

// CENTIMETERS target

        demonstrateLengthAddition(
                1.0,
                LengthUnit.INCHES,
                1.0,
                LengthUnit.INCHES,
                LengthUnit.CENTIMETERS
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
}