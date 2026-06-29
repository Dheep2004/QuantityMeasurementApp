package org.example.controller;

import org.example.entity.QuantityDTO;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.example.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementControllerTest {

    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {

        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository
                        .getInstance();

        repository.deleteAll();

        controller =

                new QuantityMeasurementController(

                        new QuantityMeasurementServiceImpl(

                                repository
                        )
                );
    }

    @Test
    void shouldCompareLengths() {

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
    }

    @Test
    void shouldConvertLength() {

        QuantityDTO result =

                controller.performConvert(

                        new QuantityDTO(

                                1,

                                "FEET",

                                "LENGTH"
                        ),

                        new QuantityDTO(

                                0,

                                "INCHES",

                                "LENGTH"
                        )
                );

        assertEquals(

                "INCHES",

                result.getUnit()
        );

        assertEquals(

                12,

                result.getValue(),

                0.01
        );
    }

    @Test
    void shouldAddLengths() {

        QuantityDTO result =

                controller.performAdd(

                        new QuantityDTO(

                                1,

                                "FEET",

                                "LENGTH"
                        ),

                        new QuantityDTO(

                                12,

                                "INCHES",

                                "LENGTH"
                        )
                );

        assertEquals(

                2,

                result.getValue(),

                0.01
        );
    }

    @Test
    void shouldSubtractLengths() {

        QuantityDTO result =

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

        assertEquals(

                9.5,

                result.getValue(),

                0.01
        );
    }

    @Test
    void shouldDivideLengths() {

        double result =

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

                5,

                result,

                0.01
        );
    }

    @Test
    void shouldReturnHistory() {

        controller.performCompare(

                new QuantityDTO(

                        1,

                        "FEET",

                        "LENGTH"
                ),

                new QuantityDTO(

                        12,

                        "INCHES",

                        "LENGTH"
                )
        );

        assertEquals(

                1,

                controller
                        .getHistoryCount()
        );
    }

    @Test
    void shouldClearHistory() {

        controller.performCompare(

                new QuantityDTO(

                        1,

                        "FEET",

                        "LENGTH"
                ),

                new QuantityDTO(

                        12,

                        "INCHES",

                        "LENGTH"
                )
        );

        controller.clearHistory();

        assertEquals(

                0,

                controller
                        .getHistoryCount()
        );
    }
}