package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.model.QuantityMeasurementEntity;
import org.example.quantity.Quantity;
import org.example.model.OperationType;
import org.example.repository.QuantityMeasurementRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.example.units.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {


    private static final Logger logger =
            LoggerFactory.getLogger(
                    QuantityMeasurementServiceImpl.class
            );

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            QuantityMeasurementRepository repository
    ) {
        this.repository = repository;
    }

    private QuantityMeasurementEntity createEntity(
            QuantityDTO first,
            QuantityDTO second,
            OperationType operation,
            String result,
            boolean error,
            String errorMessage) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(first.getValue());
        entity.setFirstUnit(first.getUnit());

        entity.setSecondValue(second.getValue());
        entity.setSecondUnit(second.getUnit());

        entity.setMeasurementType(first.getMeasurementType());

        entity.setOperation(operation);

        entity.setResult(result);

        entity.setError(error);

        entity.setErrorMessage(errorMessage);

        return entity;
    }


    private IMeasurable resolveUnit(

            String measurement,

            String unit
    ) {

        switch (
                measurement
        ) {

            case "LENGTH":

                return LengthUnit
                        .valueOf(unit);

            case "WEIGHT":

                return WeightUnit
                        .valueOf(unit);

            case "VOLUME":

                return VolumeUnit
                        .valueOf(unit);

            case "TEMPERATURE":

                return TemperatureUnit
                        .valueOf(unit);

            default:

                throw new IllegalArgumentException(

                        "Invalid measurement type"
                );
        }
    }

    private Quantity<?> convertDTO(

            QuantityDTO dto
    ) {

        IMeasurable unit =

                resolveUnit(

                        dto.getMeasurementType(),

                        dto.getUnit()
                );

        return new Quantity(

                dto.getValue(),

                unit
        );
    }

    private QuantityDTO convertResult(

            Quantity<?> result
    ) {

        return new QuantityDTO(

                result.getValue(),

                result
                        .getUnit()
                        .getUnitName(),

                result
                        .getUnit()
                        .getClass()
                        .getSimpleName()

                        .replace(
                                "Unit",
                                ""
                        )

                        .toUpperCase()
        );
    }

    @Override
    public boolean compare(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        logger.info(
                "Comparing {} and {}",
                q1,
                q2
        );

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        boolean result =

                left.equals(
                        right
                );

        repository.save(
                createEntity(
                        q1,
                        q2,
                        OperationType.COMPARE,
                        String.valueOf(result),
                        false,
                        null
                )
        );

        logger.info(
                "Comparison result: {}",
                result
        );

        return result;
    }

    @Override
    public QuantityDTO convert(

            QuantityDTO q1,

            QuantityDTO target
    ) {

        logger.info(
                "Converting {} to {}",
                q1,
                target.getUnit()
        );

        Quantity source =
                convertDTO(q1);

        Quantity result =

                source.convertTo(

                        resolveUnit(

                                target.getMeasurementType(),

                                target.getUnit()
                        )
                );

        QuantityDTO dto =
                convertResult(result);

        repository.save(
                createEntity(
                        q1,
                        target,
                        OperationType.CONVERT,
                        dto.toString(),
                        false,
                        null
                )
        );

        logger.info(
                "Conversion successful: {}",
                dto
        );

        return dto;
    }
    @Override
    public QuantityDTO add(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        logger.info(
                "Adding {} and {}",
                q1,
                q2
        );

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        Quantity result =
                left.add(
                        right
                );

        QuantityDTO dto =
                convertResult(
                        result
                );

        repository.save(
                createEntity(
                        q1,
                        q2,
                        OperationType.ADD,
                        String.valueOf(result),
                        false,
                        null
                )
        );

        logger.info(
                "Addition result: {}",
                dto
        );

        return dto;
    }

    @Override
    public QuantityDTO subtract(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        logger.info(
                "Subtracting {} and {}",
                q1,
                q2
        );

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        Quantity result =
                left.subtract(
                        right
                );

        QuantityDTO dto =
                convertResult(
                        result
                );
        repository.save(
                createEntity(
                        q1,
                        q2,
                        OperationType.SUBTRACT,
                        String.valueOf(result),
                        false,
                        null
                )
        );

        logger.info(
                "Subtraction result: {}",
                dto
        );

        return dto;
    }

    @Override
    public double divide(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        logger.info(
                "Dividing {} by {}",
                q1,
                q2
        );

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        double result =
                left.divide(
                        right
                );
        repository.save(
                createEntity(
                        q1,
                        q2,
                        OperationType.DIVIDE,
                        String.valueOf(result),
                        false,
                        null
                )
        );

        logger.info(
                "Division result: {}",
                result
        );

        return result;
    }

    @Override
    public QuantityDTO add(

            QuantityDTO q1,

            QuantityDTO q2,

            QuantityDTO target
    ) {

        logger.info(
                "Adding {} and {} in {}",
                q1,
                q2,
                target.getUnit()
        );

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        Quantity result =
                left.add(

                        right,

                        resolveUnit(

                                target.getMeasurementType(),

                                target.getUnit()
                        )
                );

        QuantityDTO dto =
                convertResult(
                        result
                );

        repository.save(
                createEntity(
                        q1,
                        q2,
                        OperationType.ADD,
                        dto.toString(),
                        false,
                        null
                )
        );

        logger.info(
                "Addition with target unit result: {}",
                dto
        );

        return dto;
    }

    @Override
    public QuantityDTO subtract(

            QuantityDTO q1,

            QuantityDTO q2,

            QuantityDTO target
    ) {

        logger.info(
                "Subtracting {} and {} in {}",
                q1,
                q2,
                target.getUnit()
        );

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        Quantity result =
                left.subtract(

                        right,

                        resolveUnit(

                                target.getMeasurementType(),

                                target.getUnit()
                        )
                );

        QuantityDTO dto =
                convertResult(
                        result
                );

        repository.save(
                createEntity(
                        q1,
                        q2,
                        OperationType.SUBTRACT,
                        String.valueOf(result),
                        false,
                        null
                )
        );

        logger.info(
                "Subtraction with target unit result: {}",
                dto
        );

        return dto;
    }
    // =========================
    // UC16 Repository Methods
    // =========================

    @Override
    public List<QuantityMeasurementEntity>
    getAllMeasurements() {

        logger.info(
                "Fetching all measurement history."
        );

        return repository.findAll();
    }

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByOperation(

            OperationType operation
    ){

        logger.info(
                "Fetching measurements by operation: {}",
                operation
        );

        return repository.findByOperation(operation);
    }

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByType(

            String measurementType
    ) {

        logger.info(
                "Fetching measurements by measurement type: {}",
                measurementType
        );

        return repository.findByMeasurementType(
                measurementType
        );
    }

    @Override
    public void clearHistory() {

        logger.info(
                "Clearing all measurement history."
        );

        repository.deleteAll();
    }

    @Override
    public long getHistoryCount() {

        logger.info(
                "Fetching total measurement count."
        );

        return repository.count();
    }
    // ==========================
// UC17 Repository Methods
// ==========================

    @Override
    public List<QuantityMeasurementEntity>
    getErroredMeasurements() {

        logger.info(
                "Fetching errored measurements."
        );

        return repository.findByErrorTrue();
    }

    @Override
    public long getOperationCount(

            OperationType operation
    ) {

        logger.info(
                "Counting {} operations.",
                operation
        );

        return repository.countByOperation(
                operation
        );
    }
}