package org.example.service;

import org.example.entity.QuantityDTO;

public interface
IQuantityMeasurementService {

    boolean compare(

            QuantityDTO q1,

            QuantityDTO q2
    );

    QuantityDTO convert(

            QuantityDTO q1,

            QuantityDTO target
    );

    QuantityDTO add(

            QuantityDTO q1,

            QuantityDTO q2
    );

    QuantityDTO subtract(

            QuantityDTO q1,

            QuantityDTO q2
    );

    double divide(

            QuantityDTO q1,

            QuantityDTO q2
    );
}