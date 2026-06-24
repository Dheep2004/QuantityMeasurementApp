package org.example;
import java.util.HashSet;

import org.example.app.QuantityMeasurementApp;
import org.example.units.IMeasurable;
import org.example.quantity.Length;
import org.example.quantity.Quantity;
import org.example.quantity.Weight;
import org.example.units.LengthUnit;
import org.example.units.TemperatureUnit;
import org.example.units.VolumeUnit;
import org.example.units.WeightUnit;
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
    @Test
    void testWeightEquality_KilogramToKilogram_SameValue() {

        Weight w1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testWeightEquality_KilogramToKilogram_DifferentValue() {

        Weight w1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(2.0, WeightUnit.KILOGRAM);

        assertFalse(w1.equals(w2));
    }

    @Test
    void testWeightEquality_KilogramToGram_EquivalentValue() {

        Weight w1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testWeightEquality_GramToKilogram_EquivalentValue() {

        Weight w1 =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight w2 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testWeightEquality_WeightVsLength_Incompatible() {

        Weight weight =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Length length =
                new Length(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

    @Test
    void testWeightEquality_NullComparison() {

        Weight weight =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertFalse(weight.equals(null));
    }

    @Test
    void testWeightEquality_SameReference() {

        Weight weight =
                new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(weight.equals(weight));
    }

    @Test
    void testWeightEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Weight(1.0, null)
        );
    }

    @Test
    void testWeightEquality_ZeroValue() {

        Weight w1 =
                new Weight(0.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(0.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testWeightEquality_NegativeWeight() {

        Weight w1 =
                new Weight(-1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(-1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testWeightConversion_PoundToKilogram() {

        Weight result =
                new Weight(
                        2.20462,
                        WeightUnit.POUND
                ).convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                1.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testWeightConversion_KilogramToPound() {

        Weight result =
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).convertTo(WeightUnit.POUND);

        assertEquals(
                2.20462,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testWeightConversion_SameUnit() {

        Weight result =
                new Weight(
                        5.0,
                        WeightUnit.KILOGRAM
                ).convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                5.0,
                result.getValue()
        );
    }

    @Test
    void testWeightConversion_RoundTrip() {

        Weight original =
                new Weight(
                        1.5,
                        WeightUnit.KILOGRAM
                );

        Weight result =
                original
                        .convertTo(WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                original.getValue(),
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testWeightAddition_SameUnit_KilogramPlusKilogram() {

        Weight w1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(2.0, WeightUnit.KILOGRAM);

        Weight result =
                w1.add(w2);

        assertEquals(
                3.0,
                result.getValue()
        );
    }

    @Test
    void testWeightAddition_CrossUnit_KilogramPlusGram() {

        Weight w1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight result =
                w1.add(w2);

        assertEquals(
                2.0,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testWeightAddition_CrossUnit_PoundPlusKilogram() {

        Weight w1 =
                new Weight(2.20462, WeightUnit.POUND);

        Weight w2 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result =
                w1.add(w2);

        assertEquals(
                4.40924,
                result.getValue(),
                0.05
        );
    }

    @Test
    void testWeightAddition_ExplicitTargetUnit_Kilogram() {

        Weight w1 =
                new Weight(1.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(1000.0, WeightUnit.GRAM);

        Weight result =
                w1.add(
                        w2,
                        WeightUnit.GRAM
                );

        assertEquals(
                2000.0,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testWeightAddition_WithZero() {

        Weight w1 =
                new Weight(5.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(0.0, WeightUnit.GRAM);

        Weight result =
                w1.add(w2);

        assertEquals(
                5.0,
                result.getValue()
        );
    }

    @Test
    void testWeightAddition_NegativeValues() {

        Weight w1 =
                new Weight(5.0, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(-2000.0, WeightUnit.GRAM);

        Weight result =
                w1.add(w2);

        assertEquals(
                3.0,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testWeightAddition_LargeValues() {

        Weight w1 =
                new Weight(1e6, WeightUnit.KILOGRAM);

        Weight w2 =
                new Weight(1e6, WeightUnit.KILOGRAM);

        Weight result =
                w1.add(w2);

        assertEquals(
                2e6,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void testWeightUnitEnum_ConversionMethods() {

        double kg =
                WeightUnit.GRAM
                        .convertToBaseUnit(1000.0);

        assertEquals(
                1.0,
                kg,
                0.0001
        );
    }

    @Test
    void testWeightHashSetBehavior() {

        HashSet<Weight> set =
                new HashSet<>();

        set.add(
                new Weight(
                        1.0,
                        WeightUnit.KILOGRAM
                )
        );

        assertEquals(1, set.size());
    }
    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {

        IMeasurable unit =
                LengthUnit.FEET;

        assertEquals(
                "FEET",
                unit.getUnitName()
        );
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {

        IMeasurable unit =
                WeightUnit.KILOGRAM;

        assertEquals(
                "KILOGRAM",
                unit.getUnitName()
        );
    }

    @Test
    void testGenericQuantity_LengthOperations_Equality() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> q2 =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> q2 =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<WeightUnit> weight =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(length.equals(weight));
    }

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).convertTo(
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion() {

        Quantity<WeightUnit> result =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                ).convertTo(
                        WeightUnit.GRAM
                );

        assertEquals(
                1000.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testGenericQuantity_LengthOperations_Addition() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> q2 =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        Quantity<LengthUnit> result =
                q1.add(
                        q2,
                        LengthUnit.FEET
                );

        assertEquals(
                2.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> q2 =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        Quantity<WeightUnit> result =
                q1.add(
                        q2,
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                2.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        1.0,
                        null
                )
        );
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testHashCode_GenericQuantity_Consistency() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> q2 =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertEquals(
                q1.hashCode(),
                q2.hashCode()
        );
    }
    //equality tests
    @Test
    void testEquality_LitreToLitre_SameValue() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> q2 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> q2 =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                );

        Quantity<VolumeUnit> q2 =
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                );

        assertTrue(q1.equals(q2));
    }
    //conversion tests
    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).convertTo(
                        VolumeUnit.MILLILITRE
                );

        assertEquals(
                1000.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                ).convertTo(
                        VolumeUnit.LITRE
                );

        assertEquals(
                3.78541,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testConversion_LitreToGallon() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                ).convertTo(
                        VolumeUnit.GALLON
                );

        assertEquals(
                1.0,
                result.getValue(),
                0.01
        );
    }
    //class category safely
    @Test
    void testEquality_VolumeVsLength_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<LengthUnit> length =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        assertFalse(volume.equals(length));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<WeightUnit> weight =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(volume.equals(weight));
    }
    //uc12

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.subtract(inches);

        Quantity<LengthUnit> expected =
                new Quantity<>(9.5, LengthUnit.FEET);

        assertEquals(expected, result);
    }
    @Test
    public void testSubtraction_ResultingInNegative() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> expected =
                new Quantity<>(-5.0, LengthUnit.FEET);

        assertEquals(expected, q1.subtract(q2));
    }
    @Test
    public void testDivision_SameUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2), 0.01);
    }
    @Test
    public void testDivision_CrossUnit() {

        Quantity<LengthUnit> inches =
                new Quantity<>(24.0, LengthUnit.INCHES);

        Quantity<LengthUnit> feet =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, inches.divide(feet), 0.01);
    }
    @Test
    public void testDivision_ByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> q1.divide(q2)
        );
    }
    @Test
    public void testSubtraction_CrossCategory() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<WeightUnit> kilogram =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,

                () -> feet.subtract((Quantity) kilogram)
        );
    }
    @Test
    void testArithmeticOperation_Add() {

        assertEquals(
                15,
                Quantity.ArithmeticOperation
                        .ADD
                        .compute(
                                10,
                                5
                        )
        );
    }

    @Test
    void testArithmeticOperation_Subtract() {

        assertEquals(
                5,
                Quantity.ArithmeticOperation
                        .SUBTRACT
                        .compute(
                                10,
                                5
                        )
        );
    }

    @Test
    void testArithmeticOperation_Divide() {

        assertEquals(
                2,
                Quantity.ArithmeticOperation
                        .DIVIDE
                        .compute(
                                10,
                                5
                        )
        );
    }
    @Test
    void testValidation_NullOperand() {

        Quantity<LengthUnit> q =
                new Quantity<>(
                        10,
                        LengthUnit.FEET
                );

        assertThrows(
                IllegalArgumentException.class,

                () ->
                        q.add(null)
        );

        assertThrows(
                IllegalArgumentException.class,

                () ->
                        q.subtract(null)
        );

        assertThrows(
                IllegalArgumentException.class,

                () ->
                        q.divide(null)
        );
    }
    @Test
    void testSubtract_BehaviorPreserved() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(
                        10,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> q2 =
                new Quantity<>(
                        6,
                        LengthUnit.INCHES
                );

        Quantity<LengthUnit> expected =
                new Quantity<>(
                        9.5,
                        LengthUnit.FEET
                );

        assertEquals(
                expected,

                q1.subtract(
                        q2
                )
        );
    }
    @Test
    void testTemperatureEquality_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(
                        0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(
                        32,
                        TemperatureUnit.FAHRENHEIT
                );

        assertTrue(
                t1.equals(t2)
        );
    }

    @Test
    void testTemperatureConversion() {

        Quantity<TemperatureUnit> result =

                new Quantity<>(
                        100,
                        TemperatureUnit.CELSIUS
                )

                        .convertTo(
                                TemperatureUnit.FAHRENHEIT
                        );

        assertEquals(
                212,
                result.getValue(),
                0.01
        );
    }

    @Test
    void testTemperatureAddUnsupported() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(
                        10,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(
                        20,
                        TemperatureUnit.CELSIUS
                );

        assertThrows(

                UnsupportedOperationException.class,

                () -> t1.add(t2)
        );
    }

    @Test
    void testTemperatureVsLength() {

        Quantity<TemperatureUnit> t =

                new Quantity<>(
                        0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<LengthUnit> l =

                new Quantity<>(
                        0,
                        LengthUnit.FEET
                );

        assertFalse(
                t.equals(l)
        );
    }
}