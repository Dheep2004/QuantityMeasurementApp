package org.example.model;

import jakarta.persistence.*;
import org.example.dto.QuantityDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "quantity_measurement")
public class QuantityMeasurementEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static final long serialVersionUID = 1L;

    @Column(name = "first_value")
    private Double firstValue;

    @Column(name = "first_unit")
    private String firstUnit;

    @Column(name = "second_value")
    private Double secondValue;

    @Column(name = "second_unit")
    private String secondUnit;

    @Column(name = "measurement_type")
    private String measurementType;

    @Enumerated(EnumType.STRING)
    private OperationType operation;

    @Column(name = "result")
    private String result;

    @Column(name = "is_error")
    private boolean error;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public QuantityMeasurementEntity() {
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

    public QuantityMeasurementEntity(

            Double firstValue,

            String firstUnit,

            Double secondValue,

            String secondUnit,

            String measurementType,

            OperationType operation,

            String result
    ) {

        this.firstValue = firstValue;

        this.firstUnit = firstUnit;

        this.secondValue = secondValue;

        this.secondUnit = secondUnit;

        this.measurementType = measurementType;

        this.operation = operation;

        this.result = result;
    }

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();

        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();
    }

    public Long getId() {

        return id;
    }

    public Double getFirstValue() {

        return firstValue;
    }

    public void setFirstValue(Double firstValue) {

        this.firstValue = firstValue;
    }

    public String getFirstUnit() {

        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {

        this.firstUnit = firstUnit;
    }

    public Double getSecondValue() {

        return secondValue;
    }

    public void setSecondValue(Double secondValue) {

        this.secondValue = secondValue;
    }

    public String getSecondUnit() {

        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {

        this.secondUnit = secondUnit;
    }

    public String getMeasurementType() {

        return measurementType;
    }

    public void setMeasurementType(String measurementType) {

        this.measurementType = measurementType;
    }

    public OperationType getOperation() {

        return operation;
    }

    public void setOperation(OperationType operation) {

        this.operation = operation;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {

        this.result = result;
    }

    public boolean isError() {

        return error;
    }

    public void setError(boolean error) {

        this.error = error;
    }


    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {

        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof QuantityMeasurementEntity))
            return false;

        QuantityMeasurementEntity that =
                (QuantityMeasurementEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}