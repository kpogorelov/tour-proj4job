package com.example.tourproj4job.controller;

import com.example.tourproj4job.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleApiException(Throwable exception, HttpServletRequest request) {
        log.error("Error occurred in endpoint {}", request.getRequestURI(), exception);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .url(request.getRequestURI())
                .error(exception.getMessage())
                .description("Something went wrong while process the request")
                .build();

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
