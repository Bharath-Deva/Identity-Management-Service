package com.flyerssoft.ims.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

  @Mock
  private JwtService jwtService;

  @InjectMocks
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  private MockHttpServletRequest request;

  private MockHttpServletResponse response;

  private FilterChain filterChain;

  @BeforeEach()
  public void setup() {
    MockitoAnnotations.openMocks(this);
    request = new MockHttpServletRequest();
    response = spy(new MockHttpServletResponse());
    filterChain = spy(new MockFilterChain());
  }

  @Test
  public void doFilterInternal_ShouldWhiteListLogin() throws Exception {
    // Set up the request
    request.setServletPath("/v1/login");

    // Execute the filter
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verify
    verify(filterChain).doFilter(request, response);
  }

  @Test
  public void doFilterInternal_ShouldWhiteListAuthenticate() throws Exception {
    // Set up the request
    request.setServletPath("/v1/authenticate");

    // Execute the filter
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain).doFilter(request, response);
  }

  @Test
  public void doFilterInternal_WhenTokenMissing_ShouldThrowError() throws Exception {
    // Set up the request
    request.setServletPath("/v1/protectedEndpoint");

    // Execute the filter
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verify
    verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
  }

  @Test
  public void doFilterInternal_WhenBearerMissing_ShouldThrowError() throws Exception {
    // Set up the request
    request.setServletPath("/v1/protectedEndpoint");
    request.addHeader("Authorization", "mockToken");

    // Execute the filter
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verify
    verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
  }

  @Test
  public void doFilterInternal_OnInValidToken_ShouldWhiteListLogin() throws Exception {
    // Set up the request
    request.setServletPath("/v1/protected-route");
    request.addHeader("Authorization", "Bearer mockToken");

    when(jwtService.isTokenValid(anyString())).thenReturn(false);

    // Execute the filter
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verify
    verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
  }
  @Test
  public void doFilterInternal_OnValidToken_ShouldSetAuthorities() throws Exception {
    // Set up the request
    request.setServletPath("/v1/protected-route");
    request.addHeader("Authorization", "Bearer mockToken");

    Claims mockClaims = new DefaultClaims();
    mockClaims.put("iss", "test_iss");
    mockClaims.put("name", "test_name");
    mockClaims.put("email", "test.email@abc.com");
    mockClaims.put("user_permissions", new ArrayList<String>());
    mockClaims.put("groups", new ArrayList<String>());

    when(jwtService.isTokenValid(anyString())).thenReturn(true);
    when(jwtService.extractAllClaims(anyString())).thenReturn(mockClaims);

    // Execute the filter
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // Verify
    verify(jwtService).extractAllClaims(anyString());
    verify(filterChain).doFilter(request, response);
  }

}

