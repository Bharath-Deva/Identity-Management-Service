package com.flyerssoft.ims.client.microsoft.auth;

import com.flyerssoft.ims.client.microsoft.MicrosoftErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Microsoft Auth Client.
 */
@Configuration
public class MicrosoftAuthClientConfig {

  /**
   * Configures the error decoder for the Microsoft Auth Client.
   *
   * @return The ErrorDecoder instance for the Microsoft Auth Client.
   */
  @Bean
  public ErrorDecoder authClientErrorDecoder() {
    return new MicrosoftErrorDecoder();
  }

  /**
   * Configures the logging level for the Feign client.
   *
   * @return The logging level for the Feign client.
   */
  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}

