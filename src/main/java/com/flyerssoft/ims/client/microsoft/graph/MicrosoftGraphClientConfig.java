package com.flyerssoft.ims.client.microsoft.graph;

import com.flyerssoft.ims.client.microsoft.MicrosoftErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign Configuration class for Microsoft Graph client.
 */
@Configuration
public class MicrosoftGraphClientConfig {

  /**
   * Bean definition for the error decoder of the Graph client.
   *
   * @return The error decoder instance.
   */
  @Bean
  public ErrorDecoder graphClientErrorDecoder() {
    return new MicrosoftErrorDecoder();
  }

}

