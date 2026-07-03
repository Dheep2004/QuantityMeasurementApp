//package org.example.controller;
//
//import org.example.dto.QuantityDTO;
//import org.example.model.OperationType;
//import org.example.model.QuantityMeasurementEntity;
//import org.example.service.IQuantityMeasurementService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//class QuantityMeasurementControllerTest {
//    private IQuantityMeasurementService service;
//
//    private QuantityMeasurementController controller;
//
//    @BeforeEach
//    void setUp() {
//
//        service = Mockito.mock(
//                IQuantityMeasurementService.class
//        );
//
//        controller =
//                new QuantityMeasurementController(
//                        service
//                );
//    }
//
//    @Test
//    void shouldCompareLengths() {
//
//        QuantityDTO feet =
//
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        QuantityDTO inches =
//
//                new QuantityDTO(
//                        12,
//                        "INCHES",
//                        "LENGTH"
//                );
//
//        when(
//                service.compare(
//                        feet,
//                        inches
//                )
//        ).thenReturn(true);
//
//        assertTrue(
//                controller.performCompare(
//                        feet,
//                        inches
//                )
//        );
//
//        verify(service)
//                .compare(
//                        feet,
//                        inches
//                );
//    }
//
//    @Test
//    void shouldConvertLength() {
//
//        QuantityDTO source =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        QuantityDTO target =
//                new QuantityDTO(
//                        0,
//                        "INCHES",
//                        "LENGTH"
//                );
//
//        QuantityDTO expected =
//                new QuantityDTO(
//                        12,
//                        "INCHES",
//                        "LENGTH"
//                );
//
//        when(
//                service.convert(
//                        source,
//                        target
//                )
//        ).thenReturn(expected);
//
//        QuantityDTO result =
//                controller.performConvert(
//                        source,
//                        target
//                );
//
//        assertEquals(
//                12,
//                result.getValue(),
//                0.01
//        );
//
//        verify(service)
//                .convert(
//                        source,
//                        target
//                );
//    }
//
//    @Test
//    void shouldAddLengths() {
//
//        QuantityDTO left =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        QuantityDTO right =
//                new QuantityDTO(
//                        12,
//                        "INCHES",
//                        "LENGTH"
//                );
//
//        QuantityDTO expected =
//                new QuantityDTO(
//                        2,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        when(
//                service.add(
//                        left,
//                        right
//                )
//        ).thenReturn(expected);
//
//        QuantityDTO result =
//                controller.performAdd(
//                        left,
//                        right
//                );
//
//        assertEquals(
//                2,
//                result.getValue(),
//                0.01
//        );
//
//        verify(service)
//                .add(
//                        left,
//                        right
//                );
//    }
//
//    @Test
//    void shouldSubtractLengths() {
//        QuantityDTO left =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        QuantityDTO right =
//                new QuantityDTO(
//                        12,
//                        "INCHES",
//                        "LENGTH"
//                );
//
//        QuantityDTO expected =
//                new QuantityDTO(
//                        2,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        when(service.subtract(left, right))
//                .thenReturn(expected);
//
//        QuantityDTO result =
//                controller.performAdd(
//                        left,
//                        right
//                );
//
//        assertEquals(
//                2,
//                result.getValue(),
//                0.01
//        );
//
//        verify(service)
//                .subtract(
//                        left,
//                        right
//                );
//    }
//
//    @Test
//    void shouldDivideLengths() {
//        QuantityDTO left =
//                new QuantityDTO(
//                        10,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        QuantityDTO right =
//                new QuantityDTO(
//                        2,
//                        "FEET",
//                        "LENGTH"
//                );
//
//        when(
//                service.divide(
//                        left,
//                        right
//                )
//        ).thenReturn(5.0);
//
//        double result =
//                controller.performDivide(
//                        left,
//                        right
//                );
//
//        assertEquals(
//                5,
//                result,
//                0.01
//        );
//
//        verify(service)
//                .divide(
//                        left,
//                        right
//                );
//    }
//    @Test
//    void shouldReturnAllMeasurements() {
//
//        List<QuantityMeasurementEntity> list =
//                new ArrayList<>();
//
//        list.add(
//                new QuantityMeasurementEntity()
//        );
//
//        when(
//                service.getAllMeasurements()
//        ).thenReturn(list);
//
//        List<QuantityMeasurementEntity> result =
//                controller.getAllMeasurements();
//
//        assertEquals(
//                1,
//                result.size()
//        );
//
//        verify(service)
//                .getAllMeasurements();
//    }
//    @Test
//    void shouldReturnHistoryCount() {
//
//        when(
//                service.getHistoryCount()
//        ).thenReturn(5L);
//
//        assertEquals(
//                5L,
//                controller.getHistoryCount()
//        );
//
//        verify(service)
//                .getHistoryCount();
//    }
//    @Test
//    void shouldClearHistory() {
//
//        controller.clearHistory();
//
//        verify(service)
//                .clearHistory();
//    }
//    @Test
//    void shouldReturnMeasurementsByType() {
//
//        when(
//                service.getMeasurementsByType(
//                        "LENGTH"
//                )
//        ).thenReturn(
//                new ArrayList<>()
//        );
//
//        assertNotNull(
//                controller.getMeasurementsByType(
//                        "LENGTH"
//                )
//        );
//
//        verify(service)
//                .getMeasurementsByType(
//                        "LENGTH"
//                );
//    }
//    @Test
//    void shouldReturnMeasurementsByOperation() {
//
//        when(
//                service.getMeasurementsByOperation(
//                        OperationType.ADD
//                )
//        ).thenReturn(
//                new ArrayList<>()
//        );
//
//        assertNotNull(
//                controller.getMeasurementsByOperation(
//                        OperationType.ADD
//                )
//        );
//
//        verify(service)
//                .getMeasurementsByOperation(
//                        OperationType.ADD
//                );
//    }
//
//}