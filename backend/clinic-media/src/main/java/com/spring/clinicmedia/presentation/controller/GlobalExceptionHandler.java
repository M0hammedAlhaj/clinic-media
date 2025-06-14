package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.domain.exception.*;
import com.spring.clinicmedia.domain.exception.booking.BookingConflictException;
import com.spring.clinicmedia.domain.exception.booking.InvalidBookingDateException;
import com.spring.clinicmedia.presentation.exception.PasswordMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> handleNotFound(ResourcesNotFoundException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Not Found");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private HashMap<String, Object> buildErrorResponse(HttpStatus status, String message) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        return response;
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<HashMap<String, Object>> handlePasswordMismatch(PasswordMismatchException ex) {
        return new ResponseEntity<>(buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<HashMap<String, Object>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Conflict");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAccountNotActivation.class)
    public ResponseEntity<HashMap<String, Object>> handleUserAccountNotActivated(UserAccountNotActivation ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("error", "Unauthorized");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DoctorNotInClinicException.class)
    public ResponseEntity<HashMap<String, Object>> handleDoctorNotInClinic(DoctorNotInClinicException ex) {
        return new ResponseEntity<>(buildErrorResponse(HttpStatus.BAD_REQUEST,
                ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBookingDateException.class)
    public ResponseEntity<HashMap<String, Object>> handleInvalidBookingDate(InvalidBookingDateException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Invalid Booking Date");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingConflictException.class)
    public ResponseEntity<HashMap<String, Object>> handleBookingConflict(BookingConflictException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Booking Conflict");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<HashMap<String, Object>> handleResourcesAlreadyExists(ResourceAlreadyExistsException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Resource Already Exists");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HashMap<String, Object>> handleIOException(IOException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "IO Error");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
