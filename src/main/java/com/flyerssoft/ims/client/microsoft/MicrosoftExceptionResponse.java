package com.flyerssoft.ims.client.microsoft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This record act as transfer object for error response
 * for microsoft graph service client.
 *
 * @param timestamp The timestamp of the exception.
 *
 * @param error The error message of the exception.
 *
 * @param errorDescription The error description of the exception.
 *
 * @param errorCodes The error codes associated with the exception.
 *
 * @param traceId The trace ID of the exception.
 *
 * @param correlationId The correlation ID of the exception.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MicrosoftExceptionResponse(
    String timestamp,
    String error,
    @JsonProperty("error_description")
    String errorDescription,
    @JsonProperty("error_codes")
    int[] errorCodes,
    @JsonProperty("trace_id")
    String traceId,
    @JsonProperty("correlation_id")
    String correlationId,

    @JsonProperty("error_uri")
    String errorUri
) {
}
