package com.flyerssoft.ims.rest.controller;


import com.flyerssoft.ims.model.dto.ImsResponse;
import com.flyerssoft.ims.model.dto.LoginResponse;
import com.flyerssoft.ims.model.dto.SignUpRequestDto;
import com.flyerssoft.ims.service.AuthService;
import com.flyerssoft.ims.utility.ImsConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller class that handles authentication-related HTTP requests and
 * manages the REST API endpoints for authentication.
 */
@RestController
@RequestMapping("/v1")
@Validated
public class AuthController {


  /**
   * The service class responsible for handling authentication business logic.
   */
  @Autowired
  private AuthService authService;

  /**
   * Appended redirection url in properties file.
   */
  @Value("${auth.signup.redirection-url}")
  private String signupRedirectionUrl;

  /**
   * Handles the HTTP GET request for user authentication.
   *
   * @param authCode the authentication code to be used for user login
   * @return the response containing the authentication result
   * @throws ParseException if there is an error parsing the authentication code
   */
  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(
      @RequestParam @NotBlank(message = "authcode is required") String authCode
  ) throws ParseException {
    HttpHeaders headers = new HttpHeaders();
    ImsResponse<?> authenticationResponse = authService.authenticate(authCode);
    if (!authenticationResponse.getStatusCode().equals(HttpStatus.TEMPORARY_REDIRECT.value())) {
      headers = new HttpHeaders();
    } else {
      // return in response headers
      headers.add(ImsConstants.REDIRECT_URL_KEY, signupRedirectionUrl);
    }
    return ResponseEntity.status(authenticationResponse.getStatusCode()).headers(headers)
        .body(authenticationResponse);
  }

  /**
   * Api to handle the sign-up request to update the employee data.
   *
   * @param accessToken   is used to authorize the request.
   * @param signUpRequest object where we are sending emergency contact number.
   * @return custom login response object which having access token.
   */
  @PutMapping("/sign-up")
  public ResponseEntity<ImsResponse<LoginResponse>> signup(
      @RequestHeader @NotBlank(message = "Access token is required") String accessToken,
      @RequestBody @Valid SignUpRequestDto signUpRequest) {
    LoginResponse serviceResponse = authService.signup(accessToken, signUpRequest);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(new ImsResponse<>(HttpStatus.ACCEPTED.value(), true, serviceResponse));
  }

  /**
   * Handles the HTTP GET request for user authentication.
   *
   * @param authorizationHeader authorization header contains basic auth.
   * @return the response containing the authentication result
   * @throws ParseException if there is an error parsing the authentication code
   */
  @PostMapping("/login")
  public ImsResponse<LoginResponse> login(
      @RequestHeader("Authorization") String authorizationHeader
  ) throws ParseException {
    return new ImsResponse<>(HttpStatus.OK.value(), true,
        authService.login(authorizationHeader));
  }
}
