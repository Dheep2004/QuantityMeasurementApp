package org.example.controller;

import org.example.entity.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.service.IQuantityMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QuantityMeasurementController {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    QuantityMeasurementController.class
            );

    private final
    IQuantityMeasurementService
            service;

    public QuantityMeasurementController(

            IQuantityMeasurementService
                    service
    ) {

        this.service =
                service;
    }

    // =========================
    // UC15 Methods
    // =========================

    public boolean performCompare(

            QuantityDTO left,

            QuantityDTO right
    ) {

        logger.info(
                "Compare request received."
        );

        return service.compare(

                left,

                right
        );
    }

    public QuantityDTO performConvert(

            QuantityDTO quantity,

            QuantityDTO target
    ) {

        logger.info(
                "Convert request received."
        );

        return service.convert(

                quantity,

                target
        );
    }

    public QuantityDTO performAdd(

            QuantityDTO left,

            QuantityDTO right
    ) {

        logger.info(
                "Add request received."
        );

        return service.add(

                left,

                right
        );
    }

    public QuantityDTO performAdd(

            QuantityDTO left,

            QuantityDTO right,

            QuantityDTO target
    ) {

        logger.info(
                "Add request with target unit received."
        );

        return service.add(

                left,

                right,

                target
        );
    }

    public QuantityDTO performSubtract(

            QuantityDTO left,

            QuantityDTO right
    ) {

        logger.info(
                "Subtract request received."
        );

        return service.subtract(

                left,

                right
        );
    }

    public QuantityDTO performSubtract(

            QuantityDTO left,

            QuantityDTO right,

            QuantityDTO target
    ) {

        logger.info(
                "Subtract request with target unit received."
        );

        return service.subtract(

                left,

                right,

                target
        );
    }

    public double performDivide(

            QuantityDTO left,

            QuantityDTO right
    ) {

        logger.info(
                "Divide request received."
        );

        return service.divide(

                left,

                right
        );
    }

    // =========================
    // UC16 Methods
    // =========================

    public List<QuantityMeasurementEntity>
    getAllMeasurements() {

        logger.info(
                "Fetching all measurements."
        );

        return service.getAllMeasurements();
    }

    public List<QuantityMeasurementEntity>
    getMeasurementsByOperation(

            String operation
    ) {

        logger.info(
                "Fetching measurements by operation: {}",
                operation
        );

        return service
                .getMeasurementsByOperation(
                        operation
                );
    }

    public List<QuantityMeasurementEntity>
    getMeasurementsByType(

            String type
    ) {

        logger.info(
                "Fetching measurements by type: {}",
                type
        );

        return service
                .getMeasurementsByType(
                        type
                );
    }

    public void clearHistory() {

        logger.info(
                "Clearing measurement history."
        );

        service.clearHistory();
    }

    public int getHistoryCount() {

        logger.info(
                "Fetching measurement count."
        );

        return service.getHistoryCount();
    }

    public void displayResult(

            Object result
    ) {

        logger.info(
                "Displaying result."
        );

        System.out.println(result);
    }
}