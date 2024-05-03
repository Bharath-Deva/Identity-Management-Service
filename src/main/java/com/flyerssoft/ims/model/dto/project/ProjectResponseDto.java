package com.flyerssoft.ims.model.dto.project;

/**
 * Represents an project data transfer object (DTO).
 *
 * @param projectId projectId
 * @param projectName projectName
 * @param employeesCount employeeCount
 */
public record ProjectResponseDto(

     int projectId,

     String projectName,

     short employeesCount
) {
}
