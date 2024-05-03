package com.flyerssoft.ims.service;

import com.flyerssoft.ims.model.dto.ImsResponse;
import com.flyerssoft.ims.model.dto.LoginResponse;
import com.flyerssoft.ims.model.dto.SignUpRequestDto;
import java.text.ParseException;

/**
 * The auth service.
 */
public interface AuthService {

  /**
   * login response.
   *
   * @param authCode authCode
   * @return login response dto
   * @throws ParseException parse exception
   */
  ImsResponse<?> authenticate(String authCode) throws ParseException;


  LoginResponse signup(String accessToken, SignUpRequestDto signUpRequest);

  LoginResponse login(String authorizationHeader) throws ParseException;
}
