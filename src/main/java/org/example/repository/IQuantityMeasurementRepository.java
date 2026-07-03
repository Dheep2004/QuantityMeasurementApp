package org.example.repository;

import org.example.model.QuantityMeasurementEntity;
import org.example.model.OperationType;
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

            OperationType operation
    );

    List<QuantityMeasurementEntity>
    getMeasurementsByType(

            String measurementType
    );

    void deleteAll();

    int getTotalCount();
}