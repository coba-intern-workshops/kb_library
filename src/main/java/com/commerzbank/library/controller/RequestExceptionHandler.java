package com.commerzbank.library.controller;

import com.commerzbank.library.dto.ResponseErrorDto;
import com.commerzbank.library.exception.RequestValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ResponseErrorDto> handleValidationException(RequestValidationException ex) {
        return ResponseEntity.badRequest().body(ResponseErrorDto.builder()
                .message(ex.getMessage())
                .build());
    }
}
