package com.flyerssoft.ims.exception;

/**
 * Not found custom exception handler class.
 */
public class NotFoundException extends ImsException {

  public static final String USER_NOT_FOUND = "User with ID %d not found";

  public static final String PROJECT_NOT_FOUND = "Project with ID %d not found";

  public static final String EMPLOYEE_NOT_FOUND = "Employee with ID %d not found";

  public static final String EMPLOYEE_EMAIL_NOT_FOUND = "Employee with email %s not found";

  public static final String TASK_NOT_FOUND = "Task with ID %d not found";

  public NotFoundException(String message) {
    super(message);
  }
}
