package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest {

    @Test
    void shouldReturnTrue_ForSameValueSameUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForDifferentFeetValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForFeetAndInchEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForFeetAndInchNotEqual() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(10.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForNullComparison() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    @Test
    void shouldReturnFalse_ForDifferentClass() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        String str = "1.0";

        assertFalse(l1.equals(str));
    }

    @Test
    void shouldReturnTrue_ForSameReference() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l1));
    }

    @Test
    void shouldReturnTrue_ForNegativeFeetValues() {
        Length l1 = new Length(-1.0, LengthUnit.FEET);
        Length l2 = new Length(-1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForPositiveAndNegativeFeet() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(-1.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForZeroFeetValues() {
        Length l1 = new Length(0.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    //  UC4 TESTcases

    @Test
    void shouldReturnTrue_ForSameYardValues() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForDifferentYardValues() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.YARDS);

        assertFalse(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForYardToFeetEquality() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForFeetToYardEquality() {
        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForYardToInchEquality() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForYardToFeetNonEquality() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForSameCentimeterValues() {
        Length l1 = new Length(2.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(2.0, LengthUnit.CENTIMETERS);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnTrue_ForCentimeterToInchEquality() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void shouldReturnFalse_ForCentimeterToFeetNonEquality() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void shouldSatisfy_TransitiveProperty() {

        Length yard = new Length(1.0, LengthUnit.YARDS);

        Length feet = new Length(3.0, LengthUnit.FEET);

        Length inch = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));

        assertTrue(feet.equals(inch));

        assertTrue(yard.equals(inch));
    }

    @Test
    void shouldReturnTrue_ForComplexMultiUnitEquality() {

        Length yard = new Length(2.0, LengthUnit.YARDS);

        Length feet = new Length(6.0, LengthUnit.FEET);

        Length inch = new Length(72.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));

        assertTrue(feet.equals(inch));

        assertTrue(yard.equals(inch));
    }


    // ---------------------------------------------------
// UC5 CONVERSION TESTS
// ---------------------------------------------------

    @Test
    void testConversion_FeetToInches() {

        double result = Length.convert(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(12.0, result, 1e-6);
    }

    @Test
    void testConversion_InchesToFeet() {

        double result = Length.convert(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        assertEquals(2.0, result, 1e-6);
    }

    @Test
    void testConversion_YardsToInches() {

        double result = Length.convert(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES
        );

        assertEquals(36.0, result, 1e-6);
    }

    @Test
    void testConversion_InchesToYards() {

        double result = Length.convert(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        assertEquals(2.0, result, 1e-6);
    }

    @Test
    void testConversion_CentimetersToInches() {

        double result = Length.convert(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testConversion_FeetToYard() {

        double result = Length.convert(
                6.0,
                LengthUnit.FEET,
                LengthUnit.YARDS
        );

        assertEquals(2.0, result, 1e-6);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {

        double original = 5.0;

        double inchValue = Length.convert(
                original,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        double feetValue = Length.convert(
                inchValue,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        assertEquals(original, feetValue, 1e-6);
    }

    @Test
    void testConversion_ZeroValue() {

        double result = Length.convert(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(0.0, result, 1e-6);
    }

    @Test
    void testConversion_NegativeValue() {

        double result = Length.convert(
                -1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        assertEquals(-12.0, result, 1e-6);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Length.convert(
                        1.0,
                        null,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Length.convert(
                        Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> Length.convert(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> Length.convert(
                        Double.NEGATIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );
    }

    @Test
    void testConversion_PrecisionTolerance() {

        double result = Length.convert(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        assertEquals(1.0, result, 1e-6);
    }
}