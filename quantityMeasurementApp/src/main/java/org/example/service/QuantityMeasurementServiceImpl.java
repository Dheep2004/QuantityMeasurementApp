package org.example.service;

import org.example.entity.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.quantity.Quantity;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.units.*;

@SuppressWarnings("unchecked")

public class
QuantityMeasurementServiceImpl

        implements
        IQuantityMeasurementService {

    private final
    IQuantityMeasurementRepository
            repository;

    public QuantityMeasurementServiceImpl(

            IQuantityMeasurementRepository
                    repository
    ) {

        this.repository =
                repository;
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

    private Quantity convertDTO(

            QuantityDTO dto
    ) {

        IMeasurable unit =

                resolveUnit(

                        dto
                                .getMeasurementType(),

                        dto
                                .getUnit()
                );

        return new Quantity(

                dto.getValue(),

                unit
        );
    }

    private QuantityDTO convertResult(

            Quantity result
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

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        boolean result =

                left.equals(
                        right
                );

        repository.save(

                new QuantityMeasurementEntity(

                        q1,

                        q2,

                        "COMPARE",

                        result
                )
        );

        return result;
    }

    @Override

    public QuantityDTO convert(

            QuantityDTO q1,

            QuantityDTO target
    ) {

        Quantity source =
                convertDTO(q1);

        Quantity result =

                source.convertTo(

                        resolveUnit(

                                target
                                        .getMeasurementType(),

                                target
                                        .getUnit()
                        )
                );

        QuantityDTO dto =
                convertResult(
                        result
                );

        repository.save(

                new QuantityMeasurementEntity(

                        q1,

                        target,

                        "CONVERT",

                        dto
                )
        );

        return dto;
    }

    @Override

    public QuantityDTO add(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

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

                new QuantityMeasurementEntity(

                        q1,

                        q2,

                        "ADD",

                        dto
                )
        );

        return dto;
    }

    @Override

    public QuantityDTO subtract(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

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

                new QuantityMeasurementEntity(

                        q1,

                        q2,

                        "SUBTRACT",

                        dto
                )
        );

        return dto;
    }

    @Override

    public double divide(

            QuantityDTO q1,

            QuantityDTO q2
    ) {

        Quantity left =
                convertDTO(q1);

        Quantity right =
                convertDTO(q2);

        double result =

                left.divide(
                        right
                );

        repository.save(

                new QuantityMeasurementEntity(

                        q1,

                        q2,

                        "DIVIDE",

                        result
                )
        );

        return result;
    }
}