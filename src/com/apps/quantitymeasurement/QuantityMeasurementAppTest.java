package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_SameValue() { //dekho if values are same
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(1.0); //agr different hain f1 f2
        Feet f2 = new Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(1.0);  //if ek null ek normal value h
        assertFalse(f1.equals(null));
    }


    @Test
    void testFeetEquality_DifferentClass() {
        Feet f1 = new Feet(1.0);      //different object type ho
        String str = "1.0";
        assertFalse(f1.equals(str));
    }
    @Test
    void testFeetEquality_NegativeValue() {
        Feet f1 = new Feet(-1.0);   //if both negative ,just to ensure it does not get confused
        Feet f2 = new Feet(-1.0);

        assertTrue(f1.equals(f2));
    }
    @Test
    void testFeetEquality_PositiveAndNegative() {
        Feet f1 = new Feet(1.0);     //ek positive ek negative
        Feet f2 = new Feet(-1.0);

        assertFalse(f1.equals(f2));
    }
    @Test
    void testFeetEquality_ZeroValue() {
        Feet f1 = new Feet(0.0);     //see if both are zero  , compiler confuse na ho ki value floating point mai -0,+0 0 h
        Feet f2 = new Feet(0.0);

        assertTrue(f1.equals(f2));
    }


    @Test
    void testFeetEquality_SameReference() {
        Feet f1 = new Feet(1.0);  //same object should always equal to itself (if (this == obj) return true)
        assertTrue(f1.equals(f1));
    }
}
