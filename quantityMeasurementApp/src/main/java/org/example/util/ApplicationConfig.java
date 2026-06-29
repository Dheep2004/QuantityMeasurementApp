package org.example.util;

import org.example.exception.DatabaseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties =
            new Properties();

    static {

        try (InputStream inputStream =
                     ApplicationConfig.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "application.properties")) {

            if (inputStream == null) {

                throw new DatabaseException(
                        "application.properties not found"
                );
            }

            properties.load(inputStream);

        } catch (IOException e) {

            throw new DatabaseException(
                    "Unable to load application.properties",
                    e
            );
        }
    }

    private ApplicationConfig() {
    }

    public static String getProperty(
            String key
    ) {

        return properties.getProperty(key);
    }
}