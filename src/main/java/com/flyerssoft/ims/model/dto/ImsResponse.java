package com.flyerssoft.ims.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Generic class representing a response in the AMS application. The
 * {@code AmsResponse} class encapsulates the response data returned by the API
 * endpoints. It includes the status code, a flag indicating the success of the
 * response, and the data payload. The generic type {@code T} represents the
 * type of data being returned in the response. This class provides a
 * standardized structure for returning responses from the AMS API.
 *
 * @param <T> the type of data being returned in the response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImsResponse<T> {

  private Integer statusCode;

  private Boolean response;

  private T data;
}
