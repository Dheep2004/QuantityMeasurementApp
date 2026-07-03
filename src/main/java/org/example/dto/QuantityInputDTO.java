package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class QuantityInputDTO
        implements Serializable {

    @Valid
    @NotNull
    private QuantityDTO thisQuantity;

    @Valid
    @NotNull
    private QuantityDTO thatQuantity;

    public QuantityInputDTO() {
    }

    public QuantityInputDTO(
            QuantityDTO thisQuantity,
            QuantityDTO thatQuantity
    ) {
        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
    }

    public QuantityDTO getThisQuantity() {
        return thisQuantity;
    }

    public void setThisQuantity(
            QuantityDTO thisQuantity
    ) {
        this.thisQuantity = thisQuantity;
    }

    public QuantityDTO getThatQuantity() {
        return thatQuantity;
    }

    public void setThatQuantity(
            QuantityDTO thatQuantity
    ) {
        this.thatQuantity = thatQuantity;
    }
}