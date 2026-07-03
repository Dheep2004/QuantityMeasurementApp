package org.example.entity;

import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementEntityTest {

    @Test
    void shouldCreateEntityUsingConstructor() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(

                        1.0,
                        "FEET",

                        12.0,
                        "INCHES",

                        "LENGTH",

                        OperationType.ADD,

                        "2.0 FEET"
                );

        assertEquals(
                1.0,
                entity.getFirstValue()
        );

        assertEquals(
                "FEET",
                entity.getFirstUnit()
        );

        assertEquals(
                12.0,
                entity.getSecondValue()
        );

        assertEquals(
                "INCHES",
                entity.getSecondUnit()
        );

        assertEquals(
                "LENGTH",
                entity.getMeasurementType()
        );

        assertEquals(
                OperationType.ADD,
                entity.getOperation()
        );

        assertEquals(
                "2.0 FEET",
                entity.getResult()
        );
    }

    @Test
    void shouldCreateEntityUsingSetters() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(10.0);

        entity.setFirstUnit("CELSIUS");

        entity.setSecondValue(20.0);

        entity.setSecondUnit("FAHRENHEIT");

        entity.setMeasurementType("TEMPERATURE");

        entity.setOperation(OperationType.COMPARE);

        entity.setResult("false");

        assertEquals(
                10.0,
                entity.getFirstValue()
        );

        assertEquals(
                "CELSIUS",
                entity.getFirstUnit()
        );

        assertEquals(
                20.0,
                entity.getSecondValue()
        );

        assertEquals(
                "FAHRENHEIT",
                entity.getSecondUnit()
        );

        assertEquals(
                "TEMPERATURE",
                entity.getMeasurementType()
        );

        assertEquals(
                OperationType.COMPARE,
                entity.getOperation()
        );

        assertEquals(
                "false",
                entity.getResult()
        );
    }

    @Test
    void shouldAllowDefaultConstructor() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        assertNotNull(entity);
    }
}