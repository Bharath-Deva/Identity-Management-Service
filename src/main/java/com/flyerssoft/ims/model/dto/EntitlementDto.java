package com.flyerssoft.ims.model.dto;

/**
 * Represents an employee data transfer object (DTO).
 *
 * @param name          Entitlement Name.
 * @param allowedMethod http method.
 * @param pathPattern   Url pattern.
 */
public record EntitlementDto(Long id, String name, String allowedMethod, String pathPattern) {
}
