package com.flyerssoft.ims.client.microsoft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyerssoft.ims.exception.UpstreamException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Custom error decoder for handling Microsoft API errors.
 */
public class MicrosoftErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder errorDecoder = new Default();

  /**
   * Decodes the error response from the Microsoft API and returns the
   * corresponding exception.
   *
   * @param methodKey The method key.
   * @param response  The response object.
   * @return The decoded exception.
   */
  public Exception decode(final String methodKey, final Response response) {
    try (InputStream bodyIs = response.body().asInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      MicrosoftExceptionResponse message = mapper.readValue(
          bodyIs, MicrosoftExceptionResponse.class);
      String errorMessage = UpstreamException.CONNECTION_TIMEOUT;
      if (Objects.nonNull(message.errorDescription())) {
        errorMessage = message.errorDescription();
      }
      return new UpstreamException(errorMessage, response.status());
    } catch (NullPointerException | IOException e) {
      return null;
    }
  }
}