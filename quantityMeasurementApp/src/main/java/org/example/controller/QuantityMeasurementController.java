package org.example.controller;

import org.example.entity.QuantityDTO;
import org.example.service.IQuantityMeasurementService;

public class
QuantityMeasurementController {

    private final
    IQuantityMeasurementService
            service;

    public QuantityMeasurementController(

            IQuantityMeasurementService
                    service
    ) {

        this.service =
                service;
    }

    public boolean compare(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        return service.compare(
                q1,
                q2
        );
    }

    public QuantityDTO convert(

            QuantityDTO q1,

            QuantityDTO target
    ) {

        return service.convert(
                q1,
                target
        );
    }

    public QuantityDTO add(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        return service.add(
                q1,
                q2
        );
    }

    public QuantityDTO subtract(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        return service.subtract(
                q1,
                q2
        );
    }

    public double divide(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        return service.divide(
                q1,
                q2
        );
    }
}