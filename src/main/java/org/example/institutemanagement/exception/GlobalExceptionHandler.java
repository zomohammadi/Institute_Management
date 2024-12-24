package org.example.institutemanagement.exception;

import org.example.institutemanagement.dto.FieldErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY);

        //Get all errors
        List<FieldErrorDto> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldErrorDto(fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(
                body, HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(FoundException.class)
    public ResponseEntity<Object> handleEntityExistsException(FoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);// HTTP 409 Conflict
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }
}
