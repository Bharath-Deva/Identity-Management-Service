package com.flyerssoft.ims.security;

import com.flyerssoft.ims.exception.UnAuthorizedException;
import com.flyerssoft.ims.model.dto.ImsErrorResponse;
import com.flyerssoft.ims.utility.ImsUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * custom filter to validate token.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final List<String> whiteListedUrls = List.of(
      "/v1/login",
      "/v1/sign-up",
      "/v1/authenticate"
  );

  @Autowired
  private JwtService jwtService;

  private boolean isUrlWhiteListed(String url) {
    return (whiteListedUrls.stream().anyMatch(url::contains));
  }

  /**
   * Custom filter to filter unAuthorized users.
   *
   * @param request     Http Servlet request
   * @param response    Http Servlet response
   * @param filterChain Spring Security filter chain
   * @throws IOException response writer throws IOException
   */
  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws IOException {
    try {
      if (isUrlWhiteListed(request.getServletPath())) {
        filterChain.doFilter(request, response);
        return;
      }
      // get auth header
      final String authorizationHeader = request.getHeader("Authorization");
      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        throw new UnAuthorizedException(UnAuthorizedException.INVALID_TOKEN);
      }

      // extract token and validate
      String token = authorizationHeader.substring(7);
      if (!jwtService.isTokenValid(token)) {
        throw new UnAuthorizedException(UnAuthorizedException.INVALID_TOKEN);
      }

      // extract entitlements from claims of token
      List<String> tokenEntitlements = (List<String>) jwtService
          .extractAllClaims(token)
          .get("user_permissions");
      Set<SimpleGrantedAuthority> entitlements = null;
      if (Objects.nonNull(tokenEntitlements)) {
        entitlements = tokenEntitlements
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
      }

      // update security context with entitlements as authorities
      // since we are only authorizing user we don't need principal and credentials.
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          null,
          null,
          entitlements
      );
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      var errorResponse = new ImsErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write(ImsUtility.convertObjectToJson(errorResponse));
    }
  }
}
