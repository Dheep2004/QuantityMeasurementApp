package org.example.repository;

import org.example.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    // UC15

    void save(

            QuantityMeasurementEntity entity
    );

    List<QuantityMeasurementEntity>
    findAll();

    // ==========================
    // UC16 Database Operations
    // ==========================

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

    void deleteAll();

    int getTotalCount();
}