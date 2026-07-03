package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.model.OperationType;
import org.example.model.QuantityMeasurementEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuantityMeasurementDTO
        implements Serializable {

    @NotNull
    private Double firstValue;

    @NotBlank
    private String firstUnit;

    @NotNull
    private Double secondValue;

    @NotBlank
    private String secondUnit;

    @NotBlank
    private String measurementType;

    @NotNull
    private OperationType operation;

    private String result;

    private boolean error;

    private String errorMessage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public QuantityMeasurementDTO() {
    }

    public QuantityMeasurementDTO(

            Double firstValue,

            String firstUnit,

            Double secondValue,

            String secondUnit,

            String measurementType,

            OperationType operation,

            String result,

            boolean error,

            String errorMessage
    ) {

        this.firstValue = firstValue;

        this.firstUnit = firstUnit;

        this.secondValue = secondValue;

        this.secondUnit = secondUnit;

        this.measurementType = measurementType;

        this.operation = operation;

        this.result = result;

        this.error = error;

        this.errorMessage = errorMessage;
    }
    public Double getFirstValue() {

        return firstValue;
    }

    public void setFirstValue(
            Double firstValue
    ) {

        this.firstValue = firstValue;
    }

    public String getFirstUnit() {

        return firstUnit;
    }

    public void setFirstUnit(
            String firstUnit
    ) {

        this.firstUnit = firstUnit;
    }

    public Double getSecondValue() {

        return secondValue;
    }

    public void setSecondValue(
            Double secondValue
    ) {

        this.secondValue = secondValue;
    }

    public String getSecondUnit() {

        return secondUnit;
    }

    public void setSecondUnit(
            String secondUnit
    ) {

        this.secondUnit = secondUnit;
    }

    public String getMeasurementType() {

        return measurementType;
    }

    public void setMeasurementType(
            String measurementType
    ) {

        this.measurementType = measurementType;
    }

    public OperationType getOperation() {

        return operation;
    }

    public void setOperation(
            OperationType operation
    ) {

        this.operation = operation;
    }

    public String getResult() {

        return result;
    }

    public void setResult(
            String result
    ) {

        this.result = result;
    }

    public boolean isError() {

        return error;
    }

    public void setError(
            boolean error
    ) {

        this.error = error;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(
            String errorMessage
    ) {

        this.errorMessage = errorMessage;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {

        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {

        return updatedAt;
    }

    public void setUpdatedAt(
            LocalDateTime updatedAt
    ) {

        this.updatedAt = updatedAt;
    }
    public static QuantityMeasurementDTO fromEntity(

            QuantityMeasurementEntity entity
    ) {

        if (entity == null) {

            return null;
        }

        QuantityMeasurementDTO dto =
                new QuantityMeasurementDTO();

        dto.setFirstValue(
                entity.getFirstValue()
        );

        dto.setFirstUnit(
                entity.getFirstUnit()
        );

        dto.setSecondValue(
                entity.getSecondValue()
        );

        dto.setSecondUnit(
                entity.getSecondUnit()
        );

        dto.setMeasurementType(
                entity.getMeasurementType()
        );

        dto.setOperation(
                entity.getOperation()
        );

        dto.setResult(
                entity.getResult()
        );

        dto.setError(
                entity.isError()
        );

        dto.setErrorMessage(
                entity.getErrorMessage()
        );

        dto.setCreatedAt(
                entity.getCreatedAt()
        );

        dto.setUpdatedAt(
                entity.getUpdatedAt()
        );

        return dto;
    }

    public QuantityMeasurementEntity toEntity() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setFirstValue(
                firstValue
        );

        entity.setFirstUnit(
                firstUnit
        );

        entity.setSecondValue(
                secondValue
        );

        entity.setSecondUnit(
                secondUnit
        );

        entity.setMeasurementType(
                measurementType
        );

        entity.setOperation(
                operation
        );

        entity.setResult(
                result
        );

        entity.setError(
                error
        );

        entity.setErrorMessage(
                errorMessage
        );

        return entity;
    }

    public static java.util.List<QuantityMeasurementDTO>
    fromEntityList(

            java.util.List<QuantityMeasurementEntity>
                    entities
    ) {

        return entities

                .stream()

                .map(
                        QuantityMeasurementDTO
                                ::fromEntity
                )

                .toList();
    }

    public static java.util.List<QuantityMeasurementEntity>
    toEntityList(

            java.util.List<QuantityMeasurementDTO>
                    dtos
    ) {

        return dtos

                .stream()

                .map(
                        QuantityMeasurementDTO
                                ::toEntity
                )

                .toList();
    }
}