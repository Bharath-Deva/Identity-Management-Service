package com.flyerssoft.ims.service.impl;

import com.flyerssoft.ims.client.microsoft.dto.Profile;
import com.flyerssoft.ims.client.microsoft.graph.MicrosoftGraphClient;
import com.flyerssoft.ims.exception.EmployeeAlreadyExistException;
import com.flyerssoft.ims.exception.NotFoundException;
import com.flyerssoft.ims.exception.UnAuthorizedException;
import com.flyerssoft.ims.mapper.EmployeeMapper;
import com.flyerssoft.ims.model.dto.EmployeeDto;
import com.flyerssoft.ims.model.dto.SignUpRequestDto;
import com.flyerssoft.ims.model.entity.Employee;
import com.flyerssoft.ims.model.entity.groupPermission;
import com.flyerssoft.ims.model.repository.EmployeeRepository;
import com.flyerssoft.ims.model.repository.UserGroupPermissionRepository;
import com.flyerssoft.ims.service.EmployeeService;
import com.flyerssoft.ims.utility.ImsConstants;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The implementation of the EmployeeService interface. This class provides
 * methods for adding employees and interacting with the EmployeeRepository.
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  /**
   * The EmployeeMapper used for mapping Profile objects to Employee objects.
   */
  @Autowired
  private EmployeeMapper employeeMapper;

  /**
   * The EmployeeRepository used for accessing and persisting Employee objects.
   */
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private UserGroupPermissionRepository userGroupPermissionRepository;

  /**
   * Holds the end points Microsoft Graph client.
   */
  @Autowired
  private MicrosoftGraphClient microsoftGraphClient;

  /**
   * Microsoft Tenant I'd which is present in application properties.
   */
  @Value("${api.microsoft.sso.tenant.id}")
  private String tenantId;

  /**
   * Microsoft Client I'd which is present in application properties.
   */
  @Value("${api.microsoft.sso.client.id}")
  private String clientId;

  /**
   * Microsoft Client secret which is present in application properties.
   */
  @Value("${api.microsoft.sso.client.secret}")
  private String clientSecret;

  /**
   * Microsoft Redirect URL which is present in application properties.
   */
  @Value("${api.microsoft.sso.redirectUri}")
  private String redirectUri;

  /**
   * Microsoft Scopes for our api to consume which is present in application
   * properties.
   */
  @Value("${api.microsoft.sso.scopes}")
  private String scopes;

  /**
   * Grant type holds the flow in which we want to achieve microsoft
   * authentication.
   */
  @Value("${api.microsoft.sso.grantType}")
  private String grantType;

  /**
   * Adds an employee based on the provided profileResponse.
   *
   * @param profileResponse The profileResponse containing the employee details.
   * @return The EmployeeDto representing the added employee.
   */
  @Override
  public EmployeeDto addEmployee(final Profile profileResponse) {
    Employee employee = this.profileResponseToEmployee(profileResponse);
    Employee savedEmployee = employeeRepository.save(employee);
    return employeeMapper.toDto(savedEmployee);
  }

  /**
   * Method to build the employee details using profile response from the graph API.
   *
   * @param profileResponse - custom object to hold the employee profile data from MS.
   * @return employee object - to store the database.
   */
  public Employee profileResponseToEmployee(Profile profileResponse) {
    Employee employee = new Employee();
    if (ObjectUtils.isNotEmpty(profileResponse)) {
      employee.setEmployeeName(profileResponse.givenName());
      employee.setEmployeeEmail(profileResponse.mail());
      employee.setEmployeeDesignation(profileResponse.jobTitle());
      employee.setEmployeeMobileNumber(profileResponse.mobilePhone());
      employee.setEmployeeLocation(profileResponse.officeLocation());
      employee.setIsActive(false);
      employee.setEmployeeCreatedDate(new Date());
      employee.setEmployeeModifiedDate(new Date());
    }
    return employee;
  }

  private List<groupPermission> getDefaultUserGroupPermissions() {
    return userGroupPermissionRepository
        .findByIsDefault(true);
  }

  @Override
  public Employee updateSignup(String accessToken, SignUpRequestDto signUpRequest) {
    Employee profileToemployee = this.profileResponseToEmployee(
        getProfileFromMicrosoft(accessToken));
    Employee employee = employeeRepository.findByEmployeeEmail(
        profileToemployee.getEmployeeEmail());
    if (ObjectUtils.isNotEmpty(employee)) {
      if (employee.getIsActive()) {
        throw new EmployeeAlreadyExistException(
            "Account already registered - " + employee.getEmployeeEmail());
      }
      employee.setEmergencyContactNumber(signUpRequest.emergencyContactNumber());
      employee.setIsActive(true);
      employee.setGroupPermissions(getDefaultUserGroupPermissions());
      Employee savedEmployee = employeeRepository.save(employee);
      return savedEmployee;
    } else {
      throw new NotFoundException(
          String.format(
              NotFoundException.EMPLOYEE_EMAIL_NOT_FOUND,
              employee.getEmployeeEmail()
          )
      );
    }
  }

  private Profile getProfileFromMicrosoft(String accessToken) {
    String bearerToken = ImsConstants.BEARER + accessToken;
    log.info("Request from user profile :{}", bearerToken);
    Profile profileResponse = null;
    try {
      profileResponse = microsoftGraphClient.getUserById(bearerToken);
      log.info("Profile response :{}", profileResponse);
    } catch (Exception ex) {
      log.error("Invalid authorization token or Unauthorized entry:{}", ex);
      throw new UnAuthorizedException("Unauthorized - Invalid authorization token");
    }
    return profileResponse;
  }

}
