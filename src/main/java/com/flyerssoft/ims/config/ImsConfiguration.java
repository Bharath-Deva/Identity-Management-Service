package com.flyerssoft.ims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The configuration class.
 */
@Configuration
public class ImsConfiguration {

  /**
   * Custom Authentication Manager Bean to avoid basic auth password generation
   * by spring security.
   */
  @Bean
  public AuthenticationManager noopAuthenticationManager() {
    return authentication -> {
      throw new AuthenticationServiceException("Authentication is disabled");
    };
  }

  /**
   * Bean which is used to encode the password of the user.
   *
   * @return new bcrypt password encoder.
   *
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
