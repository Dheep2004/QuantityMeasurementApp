//package org.example.repository;
//
//import org.example.dto.QuantityDTO;
//import org.example.model.QuantityMeasurementEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class QuantityMeasurementCacheRepositoryTest {
//
//    private QuantityMeasurementCacheRepository repository;
//
//    @BeforeEach
//    void setUp() {
//
//        repository =
//                QuantityMeasurementCacheRepository
//                        .getInstance();
//
//        repository.deleteAll();
//    }
//
//    @Test
//    void shouldSaveMeasurement() {
//
//        QuantityMeasurementEntity entity =
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                );
//
//        repository.save(entity);
//
//        assertEquals(
//                1,
//                repository.getTotalCount()
//        );
//    }
//
//    @Test
//    void shouldReturnAllMeasurements() {
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                )
//        );
//
//        List<QuantityMeasurementEntity> list =
//
//                repository.getAllMeasurements();
//
//        assertEquals(
//                1,
//                list.size()
//        );
//    }
//
//    @Test
//    void shouldDeleteAllMeasurements() {
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                )
//        );
//
//        repository.deleteAll();
//
//        assertEquals(
//                0,
//                repository.getTotalCount()
//        );
//    }
//
//    @Test
//    void shouldFilterByOperation() {
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH"
//                        ),
//
//                        "ADD",
//
//                        "2 FEET"
//                )
//        );
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                5,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                2,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        "SUBTRACT",
//
//                        "3 FEET"
//                )
//        );
//
//        List<QuantityMeasurementEntity> list =
//
//                repository.getMeasurementsByOperation(
//                        "ADD"
//                );
//
//        assertEquals(
//                1,
//                list.size()
//        );
//    }
//
//    @Test
//    void shouldFilterByMeasurementType() {
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                )
//        );
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "KILOGRAM",
//                                "WEIGHT"
//                        ),
//
//                        new QuantityDTO(
//                                1000,
//                                "GRAM",
//                                "WEIGHT"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                )
//        );
//
//        List<QuantityMeasurementEntity> list =
//
//                repository.getMeasurementsByType(
//                        "LENGTH"
//                );
//
//        assertEquals(
//                1,
//                list.size()
//        );
//    }
//
//    @Test
//    void shouldReturnCorrectCount() {
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"
//                        ),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                )
//        );
//
//        repository.save(
//
//                new QuantityMeasurementEntity(
//
//                        new QuantityDTO(
//                                1,
//                                "KILOGRAM",
//                                "WEIGHT"
//                        ),
//
//                        new QuantityDTO(
//                                1000,
//                                "GRAM",
//                                "WEIGHT"
//                        ),
//
//                        "COMPARE",
//
//                        true
//                )
//        );
//
//        assertEquals(
//                2,
//                repository.getTotalCount()
//        );
//    }
//}