package com.flyerssoft.ims.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when upstream system throw an error response. This exception
 * is typically used to indicate that there is something went wrong with
 * external dependency system.
 */
public class UpstreamException extends ImsException {

  public static final String CONNECTION_TIMEOUT = "Connection Timeout";
  private final HttpStatus status;

  /**
   * All argument constructor.
   *
   * @param customMessage represent the error message from upstream system
   * @param statusCode    status code from the upstream error response
   */
  public UpstreamException(String customMessage, int statusCode) {
    super(customMessage);
    this.status = HttpStatus.valueOf(statusCode);
  }

  public HttpStatus getStatus() {
    return status;
  }
}
