//package org.example.repository;
//
//import org.example.dto.QuantityDTO;
//import org.example.model.QuantityMeasurementEntity;
//import org.example.exception.DatabaseException;
//import org.example.util.ConnectionPool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import org.example.model.OperationType;
//public class QuantityMeasurementDatabaseRepository
//        implements IQuantityMeasurementRepository {
//
//        private static final Logger logger =
//                LoggerFactory.getLogger(
//                        QuantityMeasurementDatabaseRepository.class
//                );
//
//    @Override
//    public void save(
//            QuantityMeasurementEntity entity
//    ) {
//
//        String sql = """
//                INSERT INTO quantity_measurement
//                (
//                    first_value,
//                    first_unit,
//                    second_value,
//                    second_unit,
//                    measurement_type,
//                    operation,
//                    result
//                )
//                VALUES (?,?,?,?,?,?,?)
//                """;
//
//        try (
//
//                Connection connection =
//                        ConnectionPool.getConnection();
//
//                PreparedStatement statement =
//                        connection.prepareStatement(sql)
//
//        ) {
//
//            Double firstValue =
//                    entity.getFirstValue();
//
//            String firstUnit =
//                    entity.getFirstUnit();
//
//            Double secondValue =
//                    entity.getSecondValue();
//
//            String secondUnit =
//                    entity.getSecondUnit();
//            statement.setDouble(
//                    1,
//                    firstValue
//            );
//
//            statement.setString(
//                    2,
//                    firstUnit
//            );
//
//            statement.setDouble(
//                    3,
//                    secondValue
//            );
//
//            statement.setString(
//                    4,
//                    secondUnit
//            );
//
//            statement.setString(
//                    5,
//                    entity.getMeasurementType()
//            );
//
//            statement.setString(
//                    6,
//                    entity.getOperation().name()
//            );
//
//            statement.setString(
//                    7,
//                    String.valueOf(
//                            entity.getResult()
//                    )
//            );
//
//            statement.executeUpdate();
//            logger.info(
//                    "Measurement saved successfully."
//            );
//
//        }
//
//        catch (SQLException e) {
//
//            throw new DatabaseException(
//
//
//                    "Unable to save measurement",
//
//                    e
//
//            );
//        }
//
//    }
//
//    @Override
//    public List<QuantityMeasurementEntity> findAll() {
//
//        return getAllMeasurements();
//    }
//
//    @Override
//    public List<QuantityMeasurementEntity>
//    getAllMeasurements() {
//        logger.info(
//                "Fetching all measurements."
//        );
//
//        String sql =
//                "SELECT * FROM quantity_measurement";
//
//        return executeQuery(sql);
//    }
//
//
//    @Override
//    public List<QuantityMeasurementEntity>
//    getMeasurementsByOperation(
//
//            OperationType operation
//    ) {
//        logger.info(
//                "Fetching measurements for operation: {}",
//                operation
//        );
//
//        String sql =
//                "SELECT * FROM quantity_measurement WHERE operation=?";
//
//        return executeQuery(
//                sql,
//                operation
//        );
//    }
//
//    @Override
//    public List<QuantityMeasurementEntity>
//    getMeasurementsByType(
//            String measurementType
//    ) {
//        logger.info(
//                "Fetching measurements for type: {}",
//                measurementType
//        );
//
//        String sql =
//                "SELECT * FROM quantity_measurement WHERE measurement_type=?";
//
//        return executeQuery(
//                sql,
//                measurementType
//        );
//    }
//
//    @Override
//    public void deleteAll() {
//
//        String sql =
//                "DELETE FROM quantity_measurement";
//
//        try (
//
//                Connection connection =
//                        ConnectionPool.getConnection();
//
//                PreparedStatement statement =
//                        connection.prepareStatement(sql)
//
//        ) {
//
//            statement.executeUpdate();
//            logger.info(
//                    "All measurements deleted."
//            );
//
//        }
//
//        catch (SQLException e) {
//
//            throw new DatabaseException(
//
//                    "Unable to delete measurements",
//
//                    e
//            );
//        }
//    }
//
//    @Override
//    public int getTotalCount() {
//
//        String sql =
//                "SELECT COUNT(*) FROM quantity_measurement";
//
//        try (
//
//                Connection connection =
//                        ConnectionPool.getConnection();
//
//                PreparedStatement statement =
//                        connection.prepareStatement(sql);
//
//                ResultSet resultSet =
//                        statement.executeQuery()
//
//        ) {
//
//            resultSet.next();
//
//            int count =
//                    resultSet.getInt(1);
//
//            logger.info(
//                    "Total measurements: {}",
//                    count
//            );
//
//            return count;
//
//        }
//
//        catch (SQLException e) {
//
//            logger.error(
//                    "Failed to count measurements.",
//                    e
//            );
//
//            throw new DatabaseException(
//
//                    "Unable to count measurements",
//
//                    e
//            );
//        }
//    }
//
//    // ==========================
//    // Private helper
//    // ==========================
//
//    private List<QuantityMeasurementEntity>
//    executeQuery(
//            String sql,
//            Object... params
//    ) {
//
//        List<QuantityMeasurementEntity> list =
//                new ArrayList<>();
//
//        try (
//
//                Connection connection =
//                        ConnectionPool.getConnection();
//
//                PreparedStatement statement =
//                        connection.prepareStatement(sql)
//
//        ) {
//
//            for (
//
//                    int i = 0;
//
//                    i < params.length;
//
//                    i++
//
//            ) {
//
//                statement.setObject(
//                        i + 1,
//                        params[i]
//                );
//            }
//
//            ResultSet resultSet =
//                    statement.executeQuery();
//
//            while (resultSet.next()) {
//
//                QuantityMeasurementEntity entity =
//                        new QuantityMeasurementEntity();
//
//                entity.setFirstValue(
//                        resultSet.getDouble("first_value")
//                );
//
//                entity.setFirstUnit(
//                        resultSet.getString("first_unit")
//                );
//
//                entity.setSecondValue(
//                        resultSet.getDouble("second_value")
//                );
//
//                entity.setSecondUnit(
//                        resultSet.getString("second_unit")
//                );
//
//                entity.setMeasurementType(
//                        resultSet.getString("measurement_type")
//                );
//
//                entity.setOperation(
//                        org.example.model.OperationType.valueOf(
//                                resultSet.getString("operation")
//                        )
//                );
//
//                entity.setResult(
//                        resultSet.getString("result")
//                );
//
//                list.add(entity);
//            }
//
//            logger.info(
//                    "Fetched {} measurement(s).",
//                    list.size()
//            );
//
//            return list;
//
//        }
//
//        catch (SQLException e) {
//
//            logger.error(
//                    "Database query failed.",
//                    e
//            );
//
//            throw new DatabaseException(
//
//                    "Database query failed",
//
//                    e
//            );
//        }
//    }}