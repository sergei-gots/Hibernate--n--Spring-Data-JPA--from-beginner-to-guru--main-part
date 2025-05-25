package guru.springframework.beer.web.controller;

import guru.springframework.beer.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sergei on 24/05/2025
 */
@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFountExceptionHandler(NotFoundException e) {
        return Map.of (
                "error", "entity not found",
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public List<String> constraintValidationExceptionHandler(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>(constraintViolations.size());

        constraintViolations.forEach(
                constraintViolation -> errors.add(
                        constraintViolation.getPropertyPath() + " : " +
                                constraintViolation.getMessage()
                )
        );

        return errors;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleBindException(BindException e) {

        return e.getBindingResult().getFieldErrors()
                .stream()
                .map(
                        error ->
                                error.getField() + ": " +
                                error.getDefaultMessage())
                .collect(Collectors.toList());
    }

}
