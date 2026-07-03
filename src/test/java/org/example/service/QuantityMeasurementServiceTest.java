package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;
import org.example.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuantityMeasurementServiceTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    private QuantityDTO oneFoot;
    private QuantityDTO twelveInches;
    private QuantityDTO inchTarget;

    @BeforeEach
    void setUp() {

        oneFoot =
                new QuantityDTO(
                        1,
                        "FEET",
                        "LENGTH"
                );

        twelveInches =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "LENGTH"
                );

        inchTarget =
                new QuantityDTO(
                        0,
                        "INCHES",
                        "LENGTH"
                );
    }

    @Test
    void shouldCompareLengths() {

        boolean result =
                service.compare(
                        oneFoot,
                        twelveInches
                );

        assertTrue(result);

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }

    @Test
    void shouldConvertLength() {

        QuantityDTO result =
                service.convert(
                        oneFoot,
                        inchTarget
                );

        assertEquals(
                12,
                result.getValue(),
                0.01
        );

        assertEquals(
                "INCHES",
                result.getUnit()
        );

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }

    @Test
    void shouldAddLengths() {

        QuantityDTO result =
                service.add(
                        oneFoot,
                        twelveInches
                );

        assertEquals(
                2,
                result.getValue(),
                0.01
        );

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }
    @Test
    void shouldSubtractLengths() {

        QuantityDTO tenFeet =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO sixInches =
                new QuantityDTO(
                        6,
                        "INCHES",
                        "LENGTH"
                );

        QuantityDTO result =
                service.subtract(
                        tenFeet,
                        sixInches
                );

        assertEquals(
                9.5,
                result.getValue(),
                0.01
        );

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }

    @Test
    void shouldDivideLengths() {

        QuantityDTO tenFeet =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO twoFeet =
                new QuantityDTO(
                        2,
                        "FEET",
                        "LENGTH"
                );

        double result =
                service.divide(
                        tenFeet,
                        twoFeet
                );

        assertEquals(
                5,
                result,
                0.01
        );

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }

    @Test
    void shouldAddLengthsInTargetUnit() {

        QuantityDTO result =
                service.add(
                        oneFoot,
                        twelveInches,
                        inchTarget
                );

        assertEquals(
                24,
                result.getValue(),
                0.01
        );

        assertEquals(
                "INCHES",
                result.getUnit()
        );

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }

    @Test
    void shouldSubtractLengthsInTargetUnit() {

        QuantityDTO tenFeet =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO sixInches =
                new QuantityDTO(
                        6,
                        "INCHES",
                        "LENGTH"
                );

        QuantityDTO feetTarget =
                new QuantityDTO(
                        0,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO result =
                service.subtract(
                        tenFeet,
                        sixInches,
                        feetTarget
                );

        assertEquals(
                9.5,
                result.getValue(),
                0.01
        );

        assertEquals(
                "FEET",
                result.getUnit()
        );

        verify(repository)
                .save(any(
                        QuantityMeasurementEntity.class));
    }

    @Test
    void shouldStoreCompareOperation() {

        service.compare(
                oneFoot,
                twelveInches
        );

        ArgumentCaptor<QuantityMeasurementEntity> captor =
                ArgumentCaptor.forClass(
                        QuantityMeasurementEntity.class
                );

        verify(repository).save(
                captor.capture()
        );

        assertEquals(
                OperationType.COMPARE,
                captor.getValue().getOperation()
        );
    }

    @Test
    void shouldStoreAddOperation() {

        service.add(
                oneFoot,
                twelveInches
        );

        ArgumentCaptor<QuantityMeasurementEntity> captor =
                ArgumentCaptor.forClass(
                        QuantityMeasurementEntity.class
                );

        verify(repository).save(
                captor.capture()
        );

        assertEquals(
                OperationType.ADD,
                captor.getValue().getOperation()
        );
    }
    @Test
    void shouldReturnAllMeasurements() {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        list.add(
                new QuantityMeasurementEntity()
        );

        when(
                repository.findAll()
        ).thenReturn(
                list
        );

        List<QuantityMeasurementEntity> result =
                service.getAllMeasurements();

        assertEquals(
                1,
                result.size()
        );

        verify(repository)
                .findAll();
    }

    @Test
    void shouldReturnMeasurementsByOperation() {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        when(
                repository.findByOperation(
                        OperationType.ADD
                )
        ).thenReturn(
                list
        );

        List<QuantityMeasurementEntity> result =
                service.getMeasurementsByOperation(
                        OperationType.ADD
                );

        assertNotNull(
                result
        );

        verify(repository)
                .findByOperation(
                        OperationType.ADD
                );
    }

    @Test
    void shouldReturnMeasurementsByMeasurementType() {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        when(
                repository.findByMeasurementType(
                        "LENGTH"
                )
        ).thenReturn(
                list
        );

        List<QuantityMeasurementEntity> result =
                service.getMeasurementsByType(
                        "LENGTH"
                );

        assertNotNull(
                result
        );

        verify(repository)
                .findByMeasurementType(
                        "LENGTH"
                );
    }

    @Test
    void shouldClearHistory() {

        service.clearHistory();

        verify(repository)
                .deleteAll();
    }

    @Test
    void shouldReturnHistoryCount() {

        when(
                repository.count()
        ).thenReturn(
                5L
        );

        long count =
                service.getHistoryCount();

        assertEquals(
                5L,
                count
        );

        verify(repository)
                .count();
    }

    @Test
    void shouldStoreSubtractOperation() {

        QuantityDTO tenFeet =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO sixInches =
                new QuantityDTO(
                        6,
                        "INCHES",
                        "LENGTH"
                );

        service.subtract(
                tenFeet,
                sixInches
        );

        ArgumentCaptor<QuantityMeasurementEntity> captor =
                ArgumentCaptor.forClass(
                        QuantityMeasurementEntity.class
                );

        verify(repository)
                .save(
                        captor.capture()
                );

        assertEquals(
                OperationType.SUBTRACT,
                captor.getValue().getOperation()
        );
    }

    @Test
    void shouldStoreConvertOperation() {

        service.convert(
                oneFoot,
                inchTarget
        );

        ArgumentCaptor<QuantityMeasurementEntity> captor =
                ArgumentCaptor.forClass(
                        QuantityMeasurementEntity.class
                );

        verify(repository)
                .save(
                        captor.capture()
                );

        assertEquals(
                OperationType.CONVERT,
                captor.getValue().getOperation()
        );
    }

    @Test
    void shouldStoreDivideOperation() {

        QuantityDTO tenFeet =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO twoFeet =
                new QuantityDTO(
                        2,
                        "FEET",
                        "LENGTH"
                );

        service.divide(
                tenFeet,
                twoFeet
        );

        ArgumentCaptor<QuantityMeasurementEntity> captor =
                ArgumentCaptor.forClass(
                        QuantityMeasurementEntity.class
                );

        verify(repository)
                .save(
                        captor.capture()
                );

        assertEquals(
                OperationType.DIVIDE,
                captor.getValue().getOperation()
        );
    }
}