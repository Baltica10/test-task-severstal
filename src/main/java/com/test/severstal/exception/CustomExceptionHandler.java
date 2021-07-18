package com.test.severstal.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

  private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON;

  @ExceptionHandler(value = {com.test.severstal.exception.ValidationException.class})
  public ResponseEntity<CustomException> handleValidationException(com.test.severstal.exception.ValidationException ex) {
    logException(ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(CONTENT_TYPE)
        .body(new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(value = {com.test.severstal.exception.EntityNotFoundException.class})
  public ResponseEntity<CustomException> handleEntityNotFoundException(com.test.severstal.exception.EntityNotFoundException ex) {
    logException(ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(CONTENT_TYPE)
        .body(new CustomException(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  private void logException(Exception ex) {
    log.warn("Exception message: {}", ex.getMessage());
    log.debug("Trace: ", ex);
  }

  private static class CustomException {

    private String message;
    private int status;

    public CustomException(String message, int status) {
      this.message = message;
      this.status = status;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }
  }

}