package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {



    boolean compare(
            QuantityDTO left,
            QuantityDTO right
    );

    QuantityDTO convert(
            QuantityDTO quantity,
            QuantityDTO target
    );

    QuantityDTO add(
            QuantityDTO left,
            QuantityDTO right
    );

    QuantityDTO add(
            QuantityDTO left,
            QuantityDTO right,
            QuantityDTO target
    );

    QuantityDTO subtract(
            QuantityDTO left,
            QuantityDTO right
    );

    QuantityDTO subtract(
            QuantityDTO left,
            QuantityDTO right,
            QuantityDTO target
    );

    double divide(
            QuantityDTO left,
            QuantityDTO right
    );

    List<QuantityMeasurementEntity>
    getAllMeasurements();

    List<QuantityMeasurementEntity>
    getMeasurementsByOperation(
            OperationType operation
    );

    List<QuantityMeasurementEntity>
    getMeasurementsByType(
            String measurementType
    );

    void clearHistory();

    long getHistoryCount();
    List<QuantityMeasurementEntity>
    getErroredMeasurements();

    long getOperationCount(
            OperationType operation
    );
}