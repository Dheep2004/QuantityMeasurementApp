//package org.example.util;
//
//import org.example.exception.DatabaseException;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.util.stream.Collectors;
//
//public class DatabaseInitializer {
//
//    private DatabaseInitializer() {
//    }
//
//    public static void initialize() {
//
//        try (
//
//                Connection connection =
//                        ConnectionPool.getConnection();
//
//                Statement statement =
//                        connection.createStatement()
//
//        ) {
//
//            InputStream inputStream =
//
//                    DatabaseInitializer.class
//                            .getClassLoader()
//                            .getResourceAsStream(
//                                    "db/schema.sql"
//                            );
//
//            if (inputStream == null) {
//
//                throw new DatabaseException(
//                        "schema.sql not found"
//                );
//            }
//
//            String sql =
//
//                    new BufferedReader(
//
//                            new InputStreamReader(
//                                    inputStream
//                            )
//
//                    )
//
//                            .lines()
//
//                            .collect(
//                                    Collectors.joining("\n")
//                            );
//
//            statement.execute(sql);
//
//        }
//
//        catch (Exception e) {
//
//            throw new DatabaseException(
//
//                    "Unable to initialize database",
//
//                    e
//            );
//        }
//    }
//}