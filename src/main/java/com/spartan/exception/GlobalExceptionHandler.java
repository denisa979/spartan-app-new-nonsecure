package com.spartan.exception;

import com.spartan.dto.wrapper.ExceptionWrapper;
import com.spartan.dto.wrapper.ValidationExceptionWrapper;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> handleGenericExceptions(Throwable exception) {

        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionWrapper.builder()
                        .message("Action failed: An error occurred!")
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .localDateTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(SpartanNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleNotFoundException(Throwable exception) {

        log.error(exception.getMessage());

        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }

    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ExceptionWrapper> handleFieldValidationExceptions(FieldValidationException exception) {

        log.error(exception.getMessage());

        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .message("Invalid Input(s)")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .localDateTime(LocalDateTime.now())
                .build();

        List<ValidationExceptionWrapper> validationExceptions = collectValidationExceptions(exception);

        exceptionWrapper.setErrorCount(validationExceptions.size());
        exceptionWrapper.setValidationExceptions(validationExceptions);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);

    }

    private List<ValidationExceptionWrapper> collectValidationExceptions(FieldValidationException exception) {

        List<ValidationExceptionWrapper> validationExceptions = new ArrayList<>();

        String[] errors = exception.getMessage().split("\n");

        for (String error : errors) {

            String[] parts = error.split(":");

            if (parts.length == 2) {

                String fieldName = parts[0].trim();
                String errorMessage = parts[1].trim();

                ValidationExceptionWrapper validationException = ValidationExceptionWrapper.builder()
                        .errorField(fieldName)
                        .reason(errorMessage)
                        .build();

                validationExceptions.add(validationException);

            }

        }

        return validationExceptions;

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleValidationExceptions(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .message("Invalid Input(s)")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .localDateTime(LocalDateTime.now())
                .build();

        List<ValidationExceptionWrapper> validationExceptions = collectValidationExceptions(exception);

        exceptionWrapper.setErrorCount(validationExceptions.size());
        exceptionWrapper.setValidationExceptions(validationExceptions);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);

    }

    private List<ValidationExceptionWrapper> collectValidationExceptions(MethodArgumentNotValidException exception) {

        List<ValidationExceptionWrapper> validationExceptions = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {

            String fieldName = ((FieldError) error).getField();
            Object rejectedValue = ((FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();

            ValidationExceptionWrapper validationException = ValidationExceptionWrapper.builder()
                    .errorField(fieldName)
                    .rejectedValue(rejectedValue)
                    .reason(errorMessage)
                    .build();

            validationExceptions.add(validationException);

        }

        return validationExceptions;

    }

}
