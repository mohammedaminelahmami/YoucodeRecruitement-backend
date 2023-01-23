package com.example.youcodeRecruitment.Exception;

import com.example.youcodeRecruitment.Response.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationErrors(MethodArgumentNotValidException e) {

        Map<String, Object> errors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(400).body(new Response("Please fallout your fields carefully : " + errors, 400));
    }

//    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Response> handleRuntimeExceptions(RuntimeException e) {
//        HashMap<String, String> errors = new HashMap<>();
//        errors.put("error", "Ops something went wrong!");
//        System.out.println(e);
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Ops something went wrong! : " + errors, 500));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new Response(e.getMessage(), 400));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.badRequest().body(new Response(e.getMessage(), 400));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<Response> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(401).body(new Response("Token expired : " + e.getMessage(), 401));
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<Response> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(400).body(new Response(e.getMessage(), 400));
    }
}