package com.flyerssoft.ims.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Entitlement class represents an
 * entitlement of an api in the system.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Entitlement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "entitlement_name")
  private String name;

  @Column(name = "allowed_method")
  private String allowedMethod;

  @Column(name = "path_pattern")
  private String pathPattern;

}
