package com.flyerssoft.ims.model.entity.enums;

import lombok.Getter;

/**
 * Enumeration representing the status of a leave request.
 */
@Getter
public enum LeaveStatus {

  /**
   * The leave request is pending approval.
   */
  PENDING("Pending"),

  /**
   * The leave request has been rejected.
   */
  REJECTED("Rejected"),

  /**
   * The leave request has been approved.
   */
  APPROVED("Approved");

  /**
   * Holds the leave status message.
   */
  private final String message;

  /**
   * Constructs a LeaveStatus enum constant with the specified status.
   *
   * @param status the leave status
   */
  LeaveStatus(final String status) {
    this.message = status;
  }

  /**
   * Returns the human-readable representation of the leave status.
   *
   * @return the leave status message
   */
  public String getMessage() {
    return message;
  }
}

