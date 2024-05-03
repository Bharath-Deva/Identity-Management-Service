package com.flyerssoft.ims.exception;

/**
 * Exception thrown when user try to use api with invalid request details.
 */
public class BadRequestException extends ImsException {
  public BadRequestException(String message) {
    super(message);
  }
}
