package com.flyerssoft.ims.exception;

import lombok.Data;

/**
 * Unauthorized custom exception class.
 */
@Data
public class UnAuthorizedException extends ImsException {
  private static final long serialVersionUID = 1L;
  
  public static final String INVALID_TOKEN = "Invalid Access Token";

  public UnAuthorizedException(String message) {
    super(message);
  }
}
