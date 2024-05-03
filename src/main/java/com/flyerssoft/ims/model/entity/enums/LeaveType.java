package com.flyerssoft.ims.model.entity.enums;

import lombok.Getter;

/**
 * Enumeration representing the types of leave in the
 * Attendance Management System.
 */
@Getter
public enum LeaveType {

  /**
   * Casual leave.
   */
  CASUAL("Casual leave"),

  /**
   * Sick leave.
   */
  SICK("Sick leave"),

  /**
   * Maternity leave.
   */
  MATERNITY("Maternity leave"),

  /**
   * Paternity leave.
   */
  PATERNITY("Paternity leave"),

  /**
   * Marriage leave.
   */
  MARRIAGE("Marriage leave"),

  /**
   * Privilege leave/Earned leave.
   */
  PRIVILEGE_EARNED("Privilege leave/Earned leave");

  /**
   * Holds leave type.
   */
  private final String message;

  /**
   * Constructs a LeaveType enum constant with the specified leaveType.
   *
   * @param leaveType the descriptive leaveType of the leave type
   */
  LeaveType(final String leaveType) {
    this.message = leaveType;
  }

  /**
   * Gets the descriptive message of the leave type.
   *
   * @return the message of the leave type
   */
  public String getMessage() {
    return message;
  }
}

