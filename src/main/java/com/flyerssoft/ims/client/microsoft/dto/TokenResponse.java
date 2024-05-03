package com.flyerssoft.ims.client.microsoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the transfer object for employee profile response
 * from microsoft graph apis.
 *
 * @param scope The scope of the token.
 * @param expiresIn The expiration time in seconds.
 * @param extExpiresIn The extended expiration time in seconds.
 * @param accessToken The access token.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TokenResponse(
    String scope,

    @JsonProperty("expires_in")
    long expiresIn,

    @JsonProperty("ext_expires_in")
    long extExpiresIn,

    @JsonProperty("access_token")
    String accessToken
) {
}
