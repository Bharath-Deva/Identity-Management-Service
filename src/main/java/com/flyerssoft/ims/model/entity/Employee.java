package com.flyerssoft.ims.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The Employee class represents an employee in the system.
 */
@Getter
@Setter
@Entity
public class Employee {

  /**
   * The unique identifier of the employee.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int employeeId;

  /**
   * The name of the employee.
   */
  @Column(name = "employee_name")
  private String employeeName;

  /**
   * The email address of the employee.
   */
  @Column(name = "email", unique = true)
  private String employeeEmail;

  /**
   * The image path of the employee.
   */
  @Column(name = "image")
  private String employeeImage;

  /**
   * The mobile number of the employee.
   */
  @Column(name = "mobile_number")
  private String employeeMobileNumber;

  /**
   * The emergency contact number of the employee.
   */
  @Column(name = "emergency_contact_number")
  private String emergencyContactNumber;

  /**
   * The location of the employee.
   */
  @Column(name = "location")
  private String employeeLocation;

  /**
   * The designation of the employee.
   */
  @Column(name = "designation")
  private String employeeDesignation;

  /**
   * The official employee id of the employee.
   */
  @Column(name = "flyerssoft_id")
  private String flyerssoftId;

  /**
   * The primary manager of the employee.
   */
  @ManyToOne(cascade = {jakarta.persistence.CascadeType.ALL})
  @JoinColumn(name = "primary_manager_id")
  private Employee primaryManager;

  /**
   * The secondary manager of the employee.
   */
  @ManyToOne
  @JoinColumn(name = "secondary_manager_id")
  private Employee secondaryManager;

  /**
   * The date and time when the employee was created.
   */
  @Column(name = "createdAt")
  @CreationTimestamp
  private Date employeeCreatedDate;

  /**
   * The date and time when the employee was last modified.
   */
  @Column(name = "modifiedAt")
  @UpdateTimestamp
  private Date employeeModifiedDate;
  
  /**
   * The meta used to identify whether that is active or off.
   */
  @Column(name = "is_active")
  private Boolean isActive;

  @ManyToMany(cascade = {
      CascadeType.DETACH,
      CascadeType.MERGE,
      CascadeType.PERSIST,
      CascadeType.REFRESH
  })
  @JoinTable(
      joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "group_permission_id",
          referencedColumnName = "id"
      )
  )
  private List<groupPermission> groupPermissions;
}