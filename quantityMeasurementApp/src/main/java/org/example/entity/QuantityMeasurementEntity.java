package org.example.entity;

public class QuantityMeasurementEntity {

    private final QuantityDTO left;

    private final QuantityDTO right;

    private final String operation;

    private final Object result;

    public QuantityMeasurementEntity(

            QuantityDTO left,

            QuantityDTO right,

            String operation,

            Object result
    ) {

        this.left =
                left;

        this.right =
                right;

        this.operation =
                operation;

        this.result =
                result;
    }

    public Object getResult() {

        return result;
    }

    @Override
    public String toString() {

        return operation

                + " → "

                + result;
    }
}