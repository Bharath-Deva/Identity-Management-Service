package com.flyerssoft.ims.mapper;

import com.flyerssoft.ims.model.dto.EmployeeDto;
import com.flyerssoft.ims.model.entity.Employee;
import com.flyerssoft.ims.security.User;
import org.mapstruct.Mapper;

/**
 * The EmployeeMapper interface is responsible for mapping Employee objects to
 * EmployeeDto objects and Profile objects to Employee objects.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  Employee dtoToEntity(EmployeeDto employee1);

  EmployeeDto toDto(Employee employee);

  User toUser(Employee employee);
}
