package com.apps.quantitymeasurement;

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
    void testLengthEquality_DifferentFeetValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }



    @Test
    void testFeetAndInchEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCH);
        assertTrue(l1.equals(l2));
    }
    @Test
    void shouldReturnFalse_ForFeetAndInchNotEqual() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(10.0, LengthUnit.INCH);
        assertFalse(l1.equals(l2));

   }   @Test
    void testLengthEquality_NullComparison() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    @Test
    void testLengthEquality_DifferentClass() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        String str = "1.0";
        assertFalse(l1.equals(str));
    }

    @Test
    void testLengthEquality_SameReference() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }

    @Test
    void testLengthEquality_NegativeValue() {
        Length l1 = new Length(-1.0, LengthUnit.FEET);
        Length l2 = new Length(-1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testLengthEquality_PositiveAndNegative() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(-1.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testLengthEquality_ZeroValue() {
        Length l1 = new Length(0.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }
}