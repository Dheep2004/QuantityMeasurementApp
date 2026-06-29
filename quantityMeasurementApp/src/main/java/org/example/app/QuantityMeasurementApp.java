package org.example.app;

import org.example.controller.QuantityMeasurementController;
import org.example.entity.QuantityDTO;
import org.example.repository.QuantityMeasurementDatabaseRepository;
import org.example.service.QuantityMeasurementServiceImpl;
import org.example.util.DatabaseInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class QuantityMeasurementApp {

    public static void main(String[] args) {
        final Logger logger =
                LoggerFactory.getLogger(
                        QuantityMeasurementApp.class
                );

        logger.info(
                "Quantity Measurement Application Started."
        );

        DatabaseInitializer.initialize();

        logger.info(
                "Database initialized successfully."
        );

        QuantityMeasurementController controller =

                new QuantityMeasurementController(

                        new QuantityMeasurementServiceImpl(

                                new QuantityMeasurementDatabaseRepository()

                        )

                );
        logger.info(
                "Controller initialized successfully."
        );
        // ================= LENGTH =================

        QuantityDTO feet =
                new QuantityDTO(
                        1,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO inches =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "LENGTH"
                );

        System.out.println(
                "1 FEET == 12 INCHES : "
                        +
                        controller.performCompare(
                                feet,
                                inches
                        )
        );

        System.out.println(
                "1 FEET -> INCHES : "
                        +
                        controller.performConvert(

                                feet,

                                new QuantityDTO(

                                        0,

                                        "INCHES",

                                        "LENGTH"
                                )
                        )
        );

        System.out.println(
                "1 FEET + 12 INCHES : "
                        +
                        controller.performAdd(
                                feet,
                                inches
                        )
        );

        System.out.println(
                "10 FEET - 6 INCHES : "
                        +
                        controller.performSubtract(

                                new QuantityDTO(
                                        10,
                                        "FEET",
                                        "LENGTH"
                                ),

                                new QuantityDTO(
                                        6,
                                        "INCHES",
                                        "LENGTH"
                                )
                        )
        );

        System.out.println(
                "10 FEET / 2 FEET : "
                        +
                        controller.performDivide(

                                new QuantityDTO(
                                        10,
                                        "FEET",
                                        "LENGTH"
                                ),

                                new QuantityDTO(
                                        2,
                                        "FEET",
                                        "LENGTH"
                                )
                        )
        );

        // ================= WEIGHT =================

        QuantityDTO kilogram =
                new QuantityDTO(
                        1,
                        "KILOGRAM",
                        "WEIGHT"
                );

        QuantityDTO gram =
                new QuantityDTO(
                        1000,
                        "GRAM",
                        "WEIGHT"
                );

        System.out.println(
                "1 KG == 1000 G : "
                        +
                        controller.performCompare(
                                kilogram,
                                gram
                        )
        );

        System.out.println(
                "1 KG + 1000 G : "
                        +
                        controller.performAdd(
                                kilogram,
                                gram
                        )
        );

        // ================= VOLUME =================

        QuantityDTO litre =
                new QuantityDTO(
                        1,
                        "LITRE",
                        "VOLUME"
                );

        QuantityDTO milliLitre =
                new QuantityDTO(
                        1000,
                        "MILLILITRE",
                        "VOLUME"
                );

        System.out.println(
                "1 L == 1000 ML : "
                        +
                        controller.performCompare(
                                litre,
                                milliLitre
                        )
        );

        // ================= TEMPERATURE =================

        QuantityDTO celsius =
                new QuantityDTO(
                        0,
                        "CELSIUS",
                        "TEMPERATURE"
                );

        QuantityDTO fahrenheit =
                new QuantityDTO(
                        32,
                        "FAHRENHEIT",
                        "TEMPERATURE"
                );

        System.out.println(
                "0 C == 32 F : "
                        +
                        controller.performCompare(
                                celsius,
                                fahrenheit
                        )
        );

        System.out.println(
                "\n========== UC16 DATABASE ==========\n"
        );

        System.out.println(
                "Total Records : "
                        +
                        controller.getHistoryCount()
        );

        System.out.println("\nAll Measurements:");

        controller.getAllMeasurements()
                .forEach(System.out::println);

        System.out.println("\nADD Operations:");

        controller.getMeasurementsByOperation("ADD")
                .forEach(System.out::println);

        System.out.println("\nLENGTH Measurements:");

        controller.getMeasurementsByType("LENGTH")
                .forEach(System.out::println);

        controller.clearHistory();

        System.out.println(
                "\nHistory Cleared"
        );

        System.out.println(
                "Current Count : "
                        +
                        controller.getHistoryCount()
        );
        logger.info(
                "Quantity Measurement Application Finished."
        );
    }

}