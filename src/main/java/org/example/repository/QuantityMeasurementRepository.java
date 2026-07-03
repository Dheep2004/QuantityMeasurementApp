package org.example.repository;

import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(
            OperationType operation
    );

    List<QuantityMeasurementEntity> findByMeasurementType(
            String measurementType
    );

    List<QuantityMeasurementEntity> findByErrorTrue();

    long countByOperation(
            OperationType operation
    );
}