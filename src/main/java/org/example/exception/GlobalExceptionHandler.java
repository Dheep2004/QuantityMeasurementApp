package org.example.exception;

import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    GlobalExceptionHandler.class
            );

    @ExceptionHandler(
            IllegalArgumentException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleIllegalArgument(

            IllegalArgumentException exception
    ) {

        logger.error(
                exception.getMessage()
        );

        return buildResponse(

                HttpStatus.BAD_REQUEST,

                exception.getMessage()
        );
    }

    @ExceptionHandler(
            ArithmeticException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleArithmetic(

            ArithmeticException exception
    ) {

        logger.error(
                exception.getMessage()
        );

        return buildResponse(

                HttpStatus.BAD_REQUEST,

                exception.getMessage()
        );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleValidation(

            MethodArgumentNotValidException exception
    ) {

        String message =

                exception

                        .getBindingResult()

                        .getFieldError()

                        .getDefaultMessage();

        return buildResponse(

                HttpStatus.BAD_REQUEST,

                message
        );
    }

    @ExceptionHandler(
            ConstraintViolationException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleConstraint(

            ConstraintViolationException exception
    ) {

        return buildResponse(

                HttpStatus.BAD_REQUEST,

                exception.getMessage()
        );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<Map<String, Object>>
    handleException(

            Exception exception
    ) {

        logger.error(
                exception.getMessage()
        );

        return buildResponse(

                HttpStatus.INTERNAL_SERVER_ERROR,

                exception.getMessage()
        );
    }

    private ResponseEntity<Map<String, Object>>
    buildResponse(

            HttpStatus status,

            String message
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                LocalDateTime.now()
        );

        response.put(
                "status",
                status.value()
        );

        response.put(
                "error",
                status.getReasonPhrase()
        );

        response.put(
                "message",
                message
        );

        return new ResponseEntity<>(

                response,

                status
        );
    }
}