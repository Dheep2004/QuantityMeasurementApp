package org.example.repository;

import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuantityMeasurementRepositoryTest {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Test
    void shouldSaveMeasurement() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(1.0);
        entity.setFirstUnit("FEET");

        entity.setSecondValue(12.0);
        entity.setSecondUnit("INCHES");

        entity.setMeasurementType("LENGTH");
        entity.setOperation(OperationType.COMPARE);

        entity.setResult("true");

        repository.save(entity);

        assertNotNull(entity.getId());
    }

    @Test
    void shouldFindByOperation() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(1.0);
        entity.setFirstUnit("FEET");

        entity.setSecondValue(12.0);
        entity.setSecondUnit("INCHES");

        entity.setMeasurementType("LENGTH");

        entity.setOperation(OperationType.COMPARE);

        entity.setResult("true");

        repository.save(entity);

        List<QuantityMeasurementEntity> result =
                repository.findByOperation(
                        OperationType.COMPARE
                );

        assertFalse(result.isEmpty());
    }

    @Test
    void shouldFindByMeasurementType() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(1.0);
        entity.setFirstUnit("FEET");

        entity.setMeasurementType("LENGTH");

        entity.setOperation(OperationType.ADD);

        entity.setResult("2 FEET");

        repository.save(entity);

        List<QuantityMeasurementEntity> result =
                repository.findByMeasurementType(
                        "LENGTH"
                );

        assertFalse(result.isEmpty());
    }

    @Test
    void shouldDeleteAllMeasurements() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(1.0);
        entity.setFirstUnit("FEET");

        entity.setMeasurementType("LENGTH");

        entity.setOperation(OperationType.ADD);

        entity.setResult("2 FEET");

        repository.save(entity);

        repository.deleteAll();

        assertEquals(
                0,
                repository.count()
        );
    }

    @Test
    void shouldReturnCount() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(5.0);

        entity.setFirstUnit("KG");

        entity.setMeasurementType("WEIGHT");

        entity.setOperation(OperationType.ADD);

        entity.setResult("10 KG");

        repository.save(entity);

        assertEquals(
                1,
                repository.count()
        );
    }
}