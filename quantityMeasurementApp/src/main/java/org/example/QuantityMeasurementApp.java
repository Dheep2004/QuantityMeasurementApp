package org.example;

public class QuantityMeasurementApp {
//
//        // Inner class for Feet
//        public static class Feet {
//            private final double value;
//
//            public Feet(double value) {
//                this.value = value;
//            }
//
//            @Override
//            public boolean equals(Object obj) {
//
//                if (this == obj) return true;
//
//                if (obj == null) return false;
//
//                if (this.getClass() != obj.getClass()) return false;
//
//                Feet other = (Feet) obj;
//
//                return Double.compare(this.value, other.value) == 0;
//            }
//        }
//        public static class Inches{
//            private final double value;
//            public Inches(double value) {
//                this.value = value;
//            }
//            @Override
//            public boolean equals(Object obj){
//                if(this == obj) return true;
//                if(obj == null) return false;
//                if(this.getClass() != obj.getClass()) return false;
//                Inches other = (Inches) obj;
//                return Double.compare(this.value, other.value) == 0;
//            }
//        }
//
//        public static void main(String[] args) {
//            Feet f1 = new Feet(1.0);
//            Feet f2 = new Feet(1.0);
//
//            Inches I1 = new Inches(1.0);
//            Inches I2 = new Inches(1.0);
//
//            System.out.println(f1.equals(f2));
//            System.out.println(I1.equals(I2));
//        }
    //Demonstrates equality feature.

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
                        length1.equals(length2));
    }

    /**
     * Demonstrates comparison feature.
     */
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

    /**
     * Overloaded Method 1:
     * Conversion using raw values.
     */
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
                        convertedLength.getUnit());

        return convertedLength;
    }

    /**
     * Overloaded Method 2:
     * Conversion using existing Length object.
     */
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
                        convertedLength);

        return convertedLength;
    }

    /**
     * Main method for standalone testing.
     */
    public static void main(String[] args) {

//        demonstrateFeetEquality();
//        demonstrateInchesEquality();

        // UC3 Equality Demonstrations

        demonstrateLengthEquality(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );

        // UC4 Extended Units
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
                1.0,
                LengthUnit.CENTIMETERS,
                0.393701,
                LengthUnit.INCHES
        );

        demonstrateLengthEquality(
                30.48,
                LengthUnit.CENTIMETERS,
                1.0,
                LengthUnit.FEET
        );

        // Comparison demonstrations
        demonstrateLengthComparison(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET
        );

        // UC5 Conversion demonstrations

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

        // Overloaded method demonstration

        Length yard =
                new Length(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES
        );
    }
}


//
