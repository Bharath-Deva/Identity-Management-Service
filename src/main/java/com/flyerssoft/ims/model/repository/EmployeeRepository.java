package com.flyerssoft.ims.model.repository;

import com.flyerssoft.ims.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Employee table - Repository interface.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


  Employee findByEmployeeEmail(String employeeEmail);

}
