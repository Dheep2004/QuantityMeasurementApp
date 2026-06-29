package org.example.service;

import org.example.entity.QuantityDTO;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementServiceImplTest {

    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    void setUp() {

        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        repository.deleteAll();

        service =
                new QuantityMeasurementServiceImpl(
                        repository
                );
    }

    @Test
    void shouldCompareLengthSuccessfully() {

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
                service.compare(
                        feet,
                        inches
                )
        );
    }

    @Test
    void shouldConvertLength() {

        QuantityDTO feet =
                new QuantityDTO(
                        1,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO target =
                new QuantityDTO(
                        0,
                        "INCHES",
                        "LENGTH"
                );

        QuantityDTO result =
                service.convert(
                        feet,
                        target
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
    void shouldAddLength() {

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

        QuantityDTO result =
                service.add(
                        feet,
                        inches
                );

        assertEquals(
                2,
                result.getValue(),
                0.01
        );
    }

    @Test
    void shouldSubtractLength() {

        QuantityDTO feet =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO inches =
                new QuantityDTO(
                        6,
                        "INCHES",
                        "LENGTH"
                );

        QuantityDTO result =
                service.subtract(
                        feet,
                        inches
                );

        assertEquals(
                9.5,
                result.getValue(),
                0.01
        );
    }

    @Test
    void shouldDivideLength() {

        QuantityDTO first =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO second =
                new QuantityDTO(
                        2,
                        "FEET",
                        "LENGTH"
                );

        assertEquals(
                5,
                service.divide(
                        first,
                        second
                ),
                0.01
        );
    }

    @Test
    void shouldStoreHistory() {

        service.compare(

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
                service.getHistoryCount()
        );
    }

    @Test
    void shouldClearHistory() {

        service.compare(

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

        service.clearHistory();

        assertEquals(
                0,
                service.getHistoryCount()
        );
    }

    @Test
    void shouldReturnMeasurementsByOperation() {

        service.compare(

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
                service
                        .getMeasurementsByOperation(
                                "COMPARE"
                        )
                        .size()
        );
    }
}