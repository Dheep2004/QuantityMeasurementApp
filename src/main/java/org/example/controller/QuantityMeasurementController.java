package org.example.controller;

import jakarta.validation.Valid;

import org.example.dto.QuantityDTO;
import org.example.dto.QuantityInputDTO;
import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;
import org.example.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Quantity Measurement API",
        description = "Operations on Quantity Measurement"
)


@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    QuantityMeasurementController.class
            );

    private final
    IQuantityMeasurementService service;

    public QuantityMeasurementController(

            IQuantityMeasurementService service
    ) {

        this.service =
                service;
    }

    // ==========================
    // Compare
    // ==========================

    @Operation(
            summary = "Compare two quantities"
    )
    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(

            @Valid
            @RequestBody
            QuantityInputDTO input
    ) {

        logger.info(
                "Compare request received."
        );

        boolean result =
                service.compare(

                        input.getThisQuantity(),

                        input.getThatQuantity()
                );

        return ResponseEntity.ok(
                result
        );
    }

    // ==========================
    // Convert
    // ==========================

    @Operation(
            summary = "Convert quantity"
    )
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(

            @Valid
            @RequestBody
            QuantityInputDTO input
    ) {

        logger.info(
                "Convert request received."
        );

        QuantityDTO result =
                service.convert(

                        input.getThisQuantity(),

                        input.getThatQuantity()
                );

        return ResponseEntity.ok(
                result
        );
    }

    // ==========================
    // Add
    // ==========================

    @Operation(
            summary = "Add quantities"
    )
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(

            @Valid
            @RequestBody
            QuantityInputDTO input
    ) {

        logger.info(
                "Add request received."
        );

        QuantityDTO result =
                service.add(

                        input.getThisQuantity(),

                        input.getThatQuantity()
                );

        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                result
        );
    }
    // ==========================
    // Subtract
    // ==========================

    @Operation(
            summary = "Subtract quantities"
    )
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(

            @Valid
            @RequestBody
            QuantityInputDTO input
    ) {

        logger.info(
                "Subtract request received."
        );

        QuantityDTO result =
                service.subtract(

                        input.getThisQuantity(),

                        input.getThatQuantity()
                );

        return ResponseEntity.ok(
                result
        );
    }

    // ==========================
    // Divide
    // ==========================

    @Operation(
            summary = "Divide quantities"
    )
    @PostMapping("/divide")
    public ResponseEntity<Double> divide(

            @Valid
            @RequestBody
            QuantityInputDTO input
    ) {

        logger.info(
                "Divide request received."
        );

        double result =
                service.divide(

                        input.getThisQuantity(),

                        input.getThatQuantity()
                );

        return ResponseEntity.ok(
                result
        );
    }

    // ==========================
    // History
    // ==========================

    @Operation(
            summary = "Get all history"
    )
    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getAllMeasurements() {

        logger.info(
                "Fetching complete history."
        );

        return ResponseEntity.ok(

                service.getAllMeasurements()
        );
    }

    // ==========================
    // History By Operation
    // ==========================

    @Operation(
            summary = "History by operation"
    )
    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getMeasurementsByOperation(

            @PathVariable
            OperationType operation
    ) {

        logger.info(
                "Fetching history by operation {}",
                operation
        );

        return ResponseEntity.ok(

                service.getMeasurementsByOperation(
                        operation
                )
        );
    }

    // ==========================
    // History By Type
    // ==========================

    @Operation(
            summary = "History by type"
    )
    @GetMapping("/history/type/{type}")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getMeasurementsByType(

            @PathVariable
            String type
    ) {

        logger.info(
                "Fetching history by type {}",
                type
        );

        return ResponseEntity.ok(

                service.getMeasurementsByType(
                        type
                )
        );
    }
    // ==========================
    // Error History
    // ==========================

    @Operation(
            summary = "Errored measurements"
    )
    @GetMapping("/history/errors")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getErroredMeasurements() {

        logger.info(
                "Fetching errored measurements."
        );

        return ResponseEntity.ok(

                service.getErroredMeasurements()
        );
    }

    // ==========================
    // Count By Operation
    // ==========================

    @Operation(
            summary = "Count by operation"
    )
    @GetMapping("/history/count/{operation}")
    public ResponseEntity<Long>
    getOperationCount(

            @PathVariable
            OperationType operation
    ) {

        logger.info(
                "Fetching count for operation {}",
                operation
        );

        return ResponseEntity.ok(

                service.getOperationCount(
                        operation
                )
        );
    }

    // ==========================
    // Total Count
    // ==========================

    @Operation(
            summary = "Total history count"
    )
    @GetMapping("/history/count")
    public ResponseEntity<Long>
    getHistoryCount() {

        logger.info(
                "Fetching total history count."
        );

        return ResponseEntity.ok(

                service.getHistoryCount()
        );
    }

    // ==========================
    // Clear History
    // ==========================

    @Operation(
            summary = "Clear history"
    )
    @DeleteMapping("/history")
    public ResponseEntity<String>
    clearHistory() {

        logger.info(
                "Clearing measurement history."
        );

        service.clearHistory();

        return ResponseEntity.ok(
                "History cleared successfully."
        );
    }
}