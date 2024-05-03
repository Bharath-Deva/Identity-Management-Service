package com.flyerssoft.ims.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.flyerssoft.ims.client.microsoft.dto.Profile;

/**
 * Custom object response which we can hold employee data along with
 * the microsoft access token.
 *
 * @param profile - custom object which hold microsoft graph api data.
 * @param employeeDto - object used to store the employee details in the database.
 * @param expiresIn - access token expiration time.
 * @param accessToken - access token property.
 * @param amsToken - custom ams jwt token.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginResponseDto(
    Profile profile, EmployeeDto employeeDto,
    long expiresIn, String accessToken, String amsToken) {}
