package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementEntityTest {

    @Test
    void shouldCreateMeasurementEntity() {

        QuantityDTO left =
                new QuantityDTO(
                        1,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO right =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "LENGTH"
                );

        QuantityMeasurementEntity entity =

                new QuantityMeasurementEntity(

                        left,

                        right,

                        "COMPARE",

                        true
                );

        assertEquals(
                left,
                entity.getThisQuantity()
        );

        assertEquals(
                right,
                entity.getThatQuantity()
        );

        assertEquals(
                "COMPARE",
                entity.getOperation()
        );

        assertEquals(
                true,
                entity.getResult()
        );
    }

    @Test
    void shouldCreateErrorEntity() {

        QuantityDTO left =
                new QuantityDTO(
                        1,
                        "CELSIUS",
                        "TEMPERATURE"
                );

        QuantityDTO right =
                new QuantityDTO(
                        32,
                        "FAHRENHEIT",
                        "TEMPERATURE"
                );

        QuantityMeasurementEntity entity =

                new QuantityMeasurementEntity(

                        left,

                        right,

                        "ADD",

                        "Temperature addition not supported",

                        true
                );

        assertTrue(
                entity.isError()
        );

        assertEquals(

                "Temperature addition not supported",

                entity.getErrorMessage()
        );
    }

    @Test
    void shouldCompareEqualEntities() {

        QuantityDTO left =
                new QuantityDTO(
                        1,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO right =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "LENGTH"
                );

        QuantityMeasurementEntity entity1 =

                new QuantityMeasurementEntity(

                        left,

                        right,

                        "COMPARE",

                        true
                );

        QuantityMeasurementEntity entity2 =

                new QuantityMeasurementEntity(

                        left,

                        right,

                        "COMPARE",

                        true
                );

        assertEquals(
                entity1,
                entity2
        );

        assertEquals(
                entity1.hashCode(),
                entity2.hashCode()
        );
    }

    @Test
    void shouldReturnResultInToString() {

        QuantityMeasurementEntity entity =

                new QuantityMeasurementEntity(

                        new QuantityDTO(
                                1,
                                "FEET",
                                "LENGTH"
                        ),

                        new QuantityDTO(
                                12,
                                "INCHES",
                                "LENGTH"
                        ),

                        "COMPARE",

                        true
                );

        assertEquals(

                "true",

                entity.toString()
        );
    }
}