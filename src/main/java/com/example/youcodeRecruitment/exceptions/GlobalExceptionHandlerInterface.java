package com.example.youcodeRecruitment.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface GlobalExceptionHandlerInterface extends MessageSourceAware {
    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request);

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request);

    // handleMethodArgumentNotValid : triggers when @Valid fails
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                        HttpHeaders headers, HttpStatus status, WebRequest request);

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request);

    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                            WebRequest request);

    // handleConstraintViolationException : triggers when @Validated fails
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request);

    // handleResourceNotFoundException : triggers when there is not resource with the specified ID in BDD
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex);

    // handleNoHandlerFoundException : triggers when the handler method is invalid
    ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request);

    @ExceptionHandler({Exception.class})
    ResponseEntity<Object> handleAll(Exception ex, WebRequest request);
}
