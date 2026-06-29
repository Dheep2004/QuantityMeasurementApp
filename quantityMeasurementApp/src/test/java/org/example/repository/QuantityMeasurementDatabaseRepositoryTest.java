package org.example.repository;

import org.example.entity.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.example.util.DatabaseInitializer;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    void setUp() {

        DatabaseInitializer.initialize();

        repository =
                new QuantityMeasurementDatabaseRepository();

        repository.deleteAll();
    }

    @Test
    void shouldSaveMeasurement() {

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

        repository.save(entity);

        assertEquals(
                1,
                repository.getTotalCount()
        );
    }

    @Test
    void shouldReturnAllMeasurements() {

        repository.save(

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
                )
        );

        List<QuantityMeasurementEntity> result =

                repository.getAllMeasurements();

        assertEquals(
                1,
                result.size()
        );
    }

    @Test
    void shouldFilterByOperation() {

        repository.save(

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

                        "ADD",

                        "2 FEET"
                )
        );

        repository.save(

                new QuantityMeasurementEntity(

                        new QuantityDTO(
                                5,
                                "FEET",
                                "LENGTH"
                        ),

                        new QuantityDTO(
                                2,
                                "FEET",
                                "LENGTH"
                        ),

                        "SUBTRACT",

                        "3 FEET"
                )
        );

        List<QuantityMeasurementEntity> result =

                repository.getMeasurementsByOperation(
                        "ADD"
                );

        assertEquals(
                1,
                result.size()
        );
    }

    @Test
    void shouldFilterByMeasurementType() {

        repository.save(

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
                )
        );

        repository.save(

                new QuantityMeasurementEntity(

                        new QuantityDTO(
                                1,
                                "KILOGRAM",
                                "WEIGHT"
                        ),

                        new QuantityDTO(
                                1000,
                                "GRAM",
                                "WEIGHT"
                        ),

                        "COMPARE",

                        true
                )
        );

        List<QuantityMeasurementEntity> result =

                repository.getMeasurementsByType(
                        "LENGTH"
                );

        assertEquals(
                1,
                result.size()
        );
    }

    @Test
    void shouldDeleteAllMeasurements() {

        repository.save(

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
                )
        );

        repository.deleteAll();

        assertEquals(
                0,
                repository.getTotalCount()
        );
    }

    @Test
    void shouldReturnCorrectCount() {

        repository.save(

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
                )
        );

        repository.save(

                new QuantityMeasurementEntity(

                        new QuantityDTO(
                                1,
                                "KILOGRAM",
                                "WEIGHT"
                        ),

                        new QuantityDTO(
                                1000,
                                "GRAM",
                                "WEIGHT"
                        ),

                        "COMPARE",

                        true
                )
        );

        assertEquals(
                2,
                repository.getTotalCount()
        );
    }
}