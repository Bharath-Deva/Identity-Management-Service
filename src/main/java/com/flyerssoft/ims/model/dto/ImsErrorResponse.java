package com.flyerssoft.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an error response for the AMS API. The {@code AmsErrorResponse}
 * class encapsulates information about an error that occurred during API
 * processing. It contains details such as the status code, error message, and
 * HTTP status. This class is used to provide consistent error responses to
 * clients consuming the AMS API.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImsErrorResponse {
  private int statusCode;
  private String message;
  private final boolean error = true;
}
