package org.example.integrationTests;

import org.example.controller.QuantityMeasurementController;
import org.example.entity.QuantityDTO;
import org.example.repository.QuantityMeasurementDatabaseRepository;
import org.example.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.util.DatabaseInitializer;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {

        DatabaseInitializer.initialize();

        QuantityMeasurementDatabaseRepository repository =
                new QuantityMeasurementDatabaseRepository();

        repository.deleteAll();

        controller =

                new QuantityMeasurementController(

                        new QuantityMeasurementServiceImpl(

                                repository

                        )

                );
    }
    @Test
    void shouldPerformCompleteFlow() {

        QuantityDTO feet =

                new QuantityDTO(

                        1,

                        "FEET",

                        "LENGTH"

                );

        QuantityDTO inches =

                new QuantityDTO(

                        12,

                        "INCHES",

                        "LENGTH"

                );

        assertTrue(

                controller.performCompare(

                        feet,

                        inches

                )

        );

        controller.performAdd(

                feet,

                inches

        );

        controller.performSubtract(

                new QuantityDTO(

                        10,

                        "FEET",

                        "LENGTH"

                ),

                new QuantityDTO(

                        6,

                        "INCHES",

                        "LENGTH"

                )

        );

        controller.performDivide(

                new QuantityDTO(

                        10,

                        "FEET",

                        "LENGTH"

                ),

                new QuantityDTO(

                        2,

                        "FEET",

                        "LENGTH"

                )

        );

        assertEquals(

                4,

                controller.getHistoryCount()

        );

        assertEquals(

                1,

                controller

                        .getMeasurementsByOperation(

                                "ADD"

                        )

                        .size()

        );

        assertEquals(

                4,

                controller

                        .getMeasurementsByType(

                                "LENGTH"

                        )

                        .size()

        );

        controller.clearHistory();

        assertEquals(

                0,

                controller.getHistoryCount()

        );
    }
}