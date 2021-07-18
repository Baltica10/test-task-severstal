package com.test.severstal.exception;

public abstract class CommonException extends RuntimeException {

  public CommonException(String message, Throwable cause) {
    super(message, cause);
  }

}