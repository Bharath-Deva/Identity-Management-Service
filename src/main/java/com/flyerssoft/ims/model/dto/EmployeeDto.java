package com.flyerssoft.ims.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.flyerssoft.ims.model.entity.Employee;
import java.util.List;

/**
 * Represents an employee data transfer object (DTO).
 *
 * @param employeeId           The unique identifier of the employee.
 * @param employeeName         The name of the employee.
 * @param employeeEmail        The email address of the employee.
 * @param employeeImage        The image URL of the employee.
 * @param employeeMobileNumber The mobile number of the employee.
 * @param employeeLocation     The location of the employee.
 * @param employeeDesignation  The designation of the employee.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeDto(int employeeId,

                          String employeeName,

                          String employeeEmail,

                          String employeeImage,

                          String employeeMobileNumber,

                          String emergencyContactNumber,

                          String employeeOfficialId,

                          String employeeLocation,

                          String employeeDesignation,

                          Employee primaryManager, Employee secondaryManager,

                          List<UserGroupPermissionDto> userGroupPermissionDto) {

}
