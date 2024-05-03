package com.flyerssoft.ims.client.microsoft.auth;

import com.flyerssoft.ims.client.microsoft.dto.TokenResponse;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The microsoft auth client service.
 */
@FeignClient(
    name = "MicrosoftAuthClient",
    url = "${api.microsoft.sso.baseUrl}",
    configuration = MicrosoftAuthClientConfig.class
)
public interface MicrosoftAuthClient {

  /**
   * Get access token.
   *
   * @param tenantId tenantId
   * @param data     data
   * @return token response
   */
  @PostMapping(
      value = "{tenantId}/oauth2/v2.0/token",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  TokenResponse getAccessToken(
      @PathVariable("tenantId") String tenantId,
      @RequestBody Map<String, ?> data
  );

}
