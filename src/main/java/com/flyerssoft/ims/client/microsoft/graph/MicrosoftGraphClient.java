package com.flyerssoft.ims.client.microsoft.graph;

import com.flyerssoft.ims.client.microsoft.dto.Profile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Microsoft graph client which have all the graph end-point mapping.
 */
@FeignClient(
    name = "MicrosoftGraphClient",
    url = "${api.microsoft.graph.baseUrl}",
    configuration = MicrosoftGraphClientConfig.class
)
public interface MicrosoftGraphClient {

  /**
   * api to get the employee details from microsoft AD.
   *
   * @param bearerToken token from microsoft auth client.
   *
   * @return Profile by microsoft AD.
   */
  @GetMapping(value = "/v1.0/me")
  public Profile getUserById(
      @RequestHeader("Authorization") String bearerToken
  );

  /**
   * api to get the employee's manager from microsoft AD.
   *
   *  @param  bearerToken token from microsoft auth client.
   *
   * @return Profile of manager from microsoft AD.
   */
  @GetMapping(value = "/v1.0/me/manager")
  public Profile getManagerOfUser(
      @RequestHeader("Authorization") String bearerToken
  );

}
