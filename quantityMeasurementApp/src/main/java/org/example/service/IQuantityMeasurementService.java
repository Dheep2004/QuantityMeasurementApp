package org.example.service;

import org.example.entity.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;

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

    // ========= UC16 =========

    List<QuantityMeasurementEntity>
    getAllMeasurements();

    List<QuantityMeasurementEntity>
    getMeasurementsByOperation(
            String operation
    );

    List<QuantityMeasurementEntity>
    getMeasurementsByType(
            String measurementType
    );

    void clearHistory();

    int getHistoryCount();
}