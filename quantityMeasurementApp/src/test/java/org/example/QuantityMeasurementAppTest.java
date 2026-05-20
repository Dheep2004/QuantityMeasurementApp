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
    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        Length l1 = new Length(6.0, LengthUnit.INCHES);

        Length l2 = new Length(6.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(12.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                1.0,
                                LengthUnit.FEET,
                                12.0,
                                LengthUnit.INCHES,
                                LengthUnit.FEET
                        );

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                12.0,
                                LengthUnit.INCHES,
                                1.0,
                                LengthUnit.FEET,
                                LengthUnit.INCHES
                        );

        assertEquals(24.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                1.0,
                                LengthUnit.YARDS,
                                3.0,
                                LengthUnit.FEET,
                                LengthUnit.YARDS
                        );

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                2.54,
                                LengthUnit.CENTIMETERS,
                                1.0,
                                LengthUnit.INCHES,
                                LengthUnit.CENTIMETERS
                        );

        assertEquals(5.08, result.getValue(), 0.01);
    }

    @Test
    void testAddition_Commutativity() {

        Length result1 =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                1.0,
                                LengthUnit.FEET,
                                12.0,
                                LengthUnit.INCHES,
                                LengthUnit.FEET
                        );

        Length result2 =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                12.0,
                                LengthUnit.INCHES,
                                1.0,
                                LengthUnit.FEET,
                                LengthUnit.FEET
                        );

        assertTrue(result1.equals(result2));
    }

    @Test
    void testAddition_WithZero() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                5.0,
                                LengthUnit.FEET,
                                0.0,
                                LengthUnit.INCHES,
                                LengthUnit.FEET
                        );

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_NegativeValues() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                5.0,
                                LengthUnit.FEET,
                                -2.0,
                                LengthUnit.FEET,
                                LengthUnit.FEET
                        );

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_NullSecondOperand() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () ->
                        QuantityMeasurementApp
                                .demonstrateLengthAddition(
                                        l1,
                                        null,
                                        LengthUnit.FEET
                                )
        );
    }

    @Test
    void testAddition_LargeValues() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                1e6,
                                LengthUnit.FEET,
                                1e6,
                                LengthUnit.FEET,
                                LengthUnit.FEET
                        );

        assertEquals(2e6, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_SmallValues() {

        Length result =
                QuantityMeasurementApp
                        .demonstrateLengthAddition(
                                0.001,
                                LengthUnit.FEET,
                                0.002,
                                LengthUnit.FEET,
                                LengthUnit.FEET
                        );

        assertEquals(0.003, result.getValue(), 0.0001);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );

        assertEquals(24.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        assertEquals(0.6667, result.getValue(), 0.01);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        Length result = Length.add(
                new Length(1.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
        );

        assertEquals(5.08, result.getValue(), 0.01);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        Length result = Length.add(
                new Length(2.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.YARDS
        );

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        Length result = Length.add(
                new Length(2.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.FEET
        );

        assertEquals(9.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Length result1 = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        Length result2 = Length.add(
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.FEET),
                LengthUnit.YARDS
        );

        assertTrue(result1.equals(result2));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        Length result = Length.add(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        assertEquals(1.6667, result.getValue(), 0.01);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        Length result = Length.add(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        assertEquals(36.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Length.add(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(12.0, LengthUnit.INCHES),
                        null
                )
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        Length result = Length.add(
                new Length(1000.0, LengthUnit.FEET),
                new Length(500.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );

        assertEquals(18000.0, result.getValue(), 0.0001);
    }
    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        Length result = Length.add(
                new Length(12.0, LengthUnit.INCHES),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        assertEquals(0.6667, result.getValue(), 0.01);
    }
    // ---------------- UC8 TEST CASES ----------------

    @Test
    void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.getConversionFactor(),
                0.0001
        );
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {

        assertEquals(
                1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                0.0001
        );
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.getConversionFactor(),
                0.0001
        );
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {

        assertEquals(
                1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                0.0001
        );
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {

        assertEquals(
                5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                0.0001
        );
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                0.0001
        );
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                0.0001
        );
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {

        assertEquals(
                1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                0.0001
        );
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {

        assertEquals(
                2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                0.0001
        );
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                0.0001
        );
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {

        assertEquals(
                1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                0.0001
        );
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {

        assertEquals(
                30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                0.0001
        );
    }

    @Test
    void testQuantityLengthRefactored_Equality() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        Length l2 =
                new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        Length converted =
                l1.convertTo(LengthUnit.INCHES);

        assertEquals(
                12.0,
                converted.getValue(),
                0.0001
        );
    }

    @Test
    void testQuantityLengthRefactored_Add() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        Length l2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                l1.add(l2, LengthUnit.FEET);

        assertEquals(
                2.0,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        Length l2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                l1.add(l2, LengthUnit.YARDS);

        assertEquals(
                0.666,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null)
        );
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testBackwardCompatibility_UC1EqualityTests() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        Length l2 =
                new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testBackwardCompatibility_UC5ConversionTests() {

        double result =
                Length.convert(
                        1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result,
                0.0001
        );
    }

    @Test
    void testBackwardCompatibility_UC6AdditionTests() {

        Length l1 =
                new Length(1.0, LengthUnit.FEET);

        Length l2 =
                new Length(1.0, LengthUnit.FEET);

        Length result =
                l1.add(l2);

        assertEquals(
                2.0,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {

        Length l1 =
                new Length(1.0, LengthUnit.YARDS);

        Length l2 =
                new Length(3.0, LengthUnit.FEET);

        Length result =
                l1.add(l2, LengthUnit.YARDS);

        assertEquals(
                2.0,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testArchitecturalScalability_MultipleCategories() {

        assertNotNull(LengthUnit.FEET);

        assertNotNull(LengthUnit.INCHES);
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {

        double original = 5.0;

        double inches =
                Length.convert(
                        original,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        double result =
                Length.convert(
                        inches,
                        LengthUnit.INCHES,
                        LengthUnit.FEET
                );

        assertEquals(
                original,
                result,
                0.0001
        );
    }

    @Test
    void testUnitImmutability() {

        assertEquals(
                LengthUnit.FEET,
                LengthUnit.valueOf("FEET")
        );
    }

}