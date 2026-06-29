package org.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class QuantityMeasurementEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final QuantityDTO left;

    private final QuantityDTO right;

    private final String operation;

    private final Object result;

    private final String errorMessage;

    private final boolean isError;

    // Constructor for successful operations
    public QuantityMeasurementEntity(

            QuantityDTO left,

            QuantityDTO right,

            String operation,

            Object result
    ) {

        this.left = left;

        this.right = right;

        this.operation = operation;

        this.result = result;

        this.errorMessage = null;

        this.isError = false;
    }

    // Constructor for errors
    public QuantityMeasurementEntity(

            QuantityDTO left,

            QuantityDTO right,

            String operation,

            String errorMessage,

            boolean isError
    ) {

        this.left = left;

        this.right = right;

        this.operation = operation;

        this.result = null;

        this.errorMessage = errorMessage;

        this.isError = isError;
    }

    // ---------------- Getters ----------------

    public QuantityDTO getThisQuantity() {

        return left;
    }

    public QuantityDTO getThatQuantity() {

        return right;
    }

    public String getOperation() {

        return operation;
    }

    public Object getResult() {

        return result;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public boolean isError() {

        return isError;
    }

    // ---------------- Object methods ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj == null ||
                getClass() != obj.getClass()) {

            return false;
        }

        QuantityMeasurementEntity other =
                (QuantityMeasurementEntity) obj;

        return Objects.equals(left, other.left)
                && Objects.equals(right, other.right)
                && Objects.equals(operation, other.operation)
                && Objects.equals(result, other.result)
                && Objects.equals(errorMessage, other.errorMessage)
                && isError == other.isError;
    }

    @Override
    public int hashCode() {

        return Objects.hash(

                left,

                right,

                operation,

                result,

                errorMessage,

                isError
        );
    }

    @Override
    public String toString() {

        return isError

                ? errorMessage

                : String.valueOf(result);
    }
}