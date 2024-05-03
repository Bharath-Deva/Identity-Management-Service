package com.flyerssoft.ims.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

  @Mock
  private SecretKey secretKey;

  @Mock
  private UserDetails userDetails;

  @InjectMocks
  private JwtService jwtService;

  private String validToken;

  private final String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXN0ZXIuYWNjb3VudEBm" +
      "bHllcnNzb2Z0LmNvbSIsImlhdCI6MTY4NTYwNzA4MiwiZXhwIjoxNjg1NjkzNDgyfQ.uDfUOtpL2" +
      "hcLyk06lnUQtYvf7m_7o69-h3KOhlpLTic";

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ReflectionTestUtils.setField(
        jwtService, "secretKey", "2D4A614E645266556A586E3272357538782F413F4428472B4B6250655368566B"
    );
    ReflectionTestUtils.setField(
        jwtService, "jwtExpiration", 1000000
    );

    //generate token
    Map<String, Object> customClaims = new HashMap<>();
    customClaims.put("iss", "test_iss");
    customClaims.put("name", "test name");
    customClaims.put("email", "test.email@abc.com");

    this.validToken = jwtService.generateToken(customClaims, new User());
  }

  @Test
  void validateToken_InvalidToken_ShouldReturnFalse() {
    boolean result = jwtService.isTokenValid(invalidToken);

    Assertions.assertFalse(result);
  }

  @Test
  void isTokenValid_ValidTokenAndNotExpired_ShouldReturnTrue() {
    // Assert
    Assertions.assertTrue(
        jwtService.isTokenValid(validToken)
    );
  }

  @Test
  void extractClaim_Success() {
    String tokenSubject = jwtService.extractClaim(validToken, Claims::getSubject);
    assertEquals("null", tokenSubject);
  }
  @Test
  void extractExpiration_Success() {
    Date issuedAtDate = jwtService.extractClaim(validToken, Claims::getIssuedAt);
    Date expirationDate = jwtService.extractExpiration(validToken);
    long expirationDuration = expirationDate.getTime() - issuedAtDate.getTime();
    assertEquals(1000000, expirationDuration);
  }
}



