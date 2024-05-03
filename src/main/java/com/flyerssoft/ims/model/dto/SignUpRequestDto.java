package com.flyerssoft.ims.model.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Custom object response used to hold the requested emergency contact number.
 *
 * @param emergencyContactNumber - contact property to update in the employee table.
 *
 */
public record SignUpRequestDto(
    @NotBlank(message = "Emergency contact number is required") String emergencyContactNumber) {

}
