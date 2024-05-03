package com.flyerssoft.ims.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Set;

/**
 * This record will set transfer object on successful login.
 *
 * @param profile The profile associated with the login response.
 * @param expiresIn The expiration time of the access token.
 * @param accessToken The access token.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginResponse(
    EmployeeDto profile,
    long expiresIn,
    String accessToken,
    List<String> userGroupPermissions,
    Set<EntitlementDto> entitlements
) {}
