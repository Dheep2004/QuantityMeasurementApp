package org.example.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final BasicDataSource dataSource =
            new BasicDataSource();

    static {

        dataSource.setDriverClassName(

                ApplicationConfig.getProperty(
                        "db.driver"
                )
        );

        dataSource.setUrl(

                ApplicationConfig.getProperty(
                        "db.url"
                )
        );

        dataSource.setUsername(

                ApplicationConfig.getProperty(
                        "db.username"
                )
        );

        dataSource.setPassword(

                ApplicationConfig.getProperty(
                        "db.password"
                )
        );

        dataSource.setInitialSize(

                Integer.parseInt(

                        ApplicationConfig.getProperty(
                                "db.initialSize"
                        )
                )
        );

        dataSource.setMaxTotal(

                Integer.parseInt(

                        ApplicationConfig.getProperty(
                                "db.maxTotal"
                        )
                )
        );
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() {

        try {

            return dataSource.getConnection();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to create database connection",
                    e
            );
        }
    }
}