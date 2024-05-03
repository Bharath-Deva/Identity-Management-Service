package com.flyerssoft.ims.exception;

/**
 * EmployeeAlreadyExistException class.
 */
public class EmployeeAlreadyExistException extends ImsException {
  private static final long serialVersionUID = 1L;

  public EmployeeAlreadyExistException(String message) {
    super(message);
  }
}
