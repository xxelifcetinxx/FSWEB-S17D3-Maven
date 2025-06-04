package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException ex) {
        ZooErrorResponse error = new ZooErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        log.error("ZooException: {}", ex.getMessage());
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception ex) {
        ZooErrorResponse error = new ZooErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        log.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
