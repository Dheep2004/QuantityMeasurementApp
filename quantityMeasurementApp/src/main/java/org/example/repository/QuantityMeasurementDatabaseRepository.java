package org.example.repository;

import org.example.entity.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.exception.DatabaseException;
import org.example.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

        private static final Logger logger =
                LoggerFactory.getLogger(
                        QuantityMeasurementDatabaseRepository.class
                );

    @Override
    public void save(
            QuantityMeasurementEntity entity
    ) {

        String sql = """
                INSERT INTO quantity_measurement
                (
                    first_value,
                    first_unit,
                    second_value,
                    second_unit,
                    measurement_type,
                    operation,
                    result
                )
                VALUES (?,?,?,?,?,?,?)
                """;

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            QuantityDTO first =
                    entity.getThisQuantity();

            QuantityDTO second =
                    entity.getThatQuantity();

            statement.setDouble(
                    1,
                    first.getValue()
            );

            statement.setString(
                    2,
                    first.getUnit()
            );

            statement.setDouble(
                    3,
                    second.getValue()
            );

            statement.setString(
                    4,
                    second.getUnit()
            );

            statement.setString(
                    5,
                    first.getMeasurementType()
            );

            statement.setString(
                    6,
                    entity.getOperation()
            );

            statement.setString(
                    7,
                    String.valueOf(
                            entity.getResult()
                    )
            );

            statement.executeUpdate();
            logger.info(
                    "Measurement saved successfully."
            );

        }

        catch (SQLException e) {

            throw new DatabaseException(


                    "Unable to save measurement",

                    e

            );
        }

    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        return getAllMeasurements();
    }

    @Override
    public List<QuantityMeasurementEntity>
    getAllMeasurements() {
        logger.info(
                "Fetching all measurements."
        );

        String sql =
                "SELECT * FROM quantity_measurement";

        return executeQuery(sql);
    }

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByOperation(
            String operation
    ) {
        logger.info(
                "Fetching measurements for operation: {}",
                operation
        );

        String sql =
                "SELECT * FROM quantity_measurement WHERE operation=?";

        return executeQuery(
                sql,
                operation
        );
    }

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByType(
            String measurementType
    ) {
        logger.info(
                "Fetching measurements for type: {}",
                measurementType
        );

        String sql =
                "SELECT * FROM quantity_measurement WHERE measurement_type=?";

        return executeQuery(
                sql,
                measurementType
        );
    }

    @Override
    public void deleteAll() {

        String sql =
                "DELETE FROM quantity_measurement";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.executeUpdate();
            logger.info(
                    "All measurements deleted."
            );

        }

        catch (SQLException e) {

            throw new DatabaseException(

                    "Unable to delete measurements",

                    e
            );
        }
    }

    @Override
    public int getTotalCount() {

        String sql =
                "SELECT COUNT(*) FROM quantity_measurement";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            resultSet.next();

            int count =
                    resultSet.getInt(1);

            logger.info(
                    "Total measurements: {}",
                    count
            );

            return count;

        }

        catch (SQLException e) {

            logger.error(
                    "Failed to count measurements.",
                    e
            );

            throw new DatabaseException(

                    "Unable to count measurements",

                    e
            );
        }
    }

    // ==========================
    // Private helper
    // ==========================

    private List<QuantityMeasurementEntity>
    executeQuery(
            String sql,
            Object... params
    ) {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            for (

                    int i = 0;

                    i < params.length;

                    i++

            ) {

                statement.setObject(
                        i + 1,
                        params[i]
                );
            }

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                QuantityDTO first =
                        new QuantityDTO(

                                resultSet.getDouble(
                                        "first_value"
                                ),

                                resultSet.getString(
                                        "first_unit"
                                ),

                                resultSet.getString(
                                        "measurement_type"
                                )
                        );

                QuantityDTO second =
                        new QuantityDTO(

                                resultSet.getDouble(
                                        "second_value"
                                ),

                                resultSet.getString(
                                        "second_unit"
                                ),

                                resultSet.getString(
                                        "measurement_type"
                                )
                        );

                QuantityMeasurementEntity entity =
                        new QuantityMeasurementEntity(

                                first,

                                second,

                                resultSet.getString(
                                        "operation"
                                ),

                                resultSet.getString(
                                        "result"
                                )
                        );

                list.add(entity);
            }

            logger.info(
                    "Fetched {} measurement(s).",
                    list.size()
            );

            return list;

        }

        catch (SQLException e) {

            logger.error(
                    "Database query failed.",
                    e
            );

            throw new DatabaseException(

                    "Database query failed",

                    e
            );
        }
    }}