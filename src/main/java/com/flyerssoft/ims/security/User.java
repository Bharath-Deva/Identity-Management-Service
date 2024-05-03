package com.flyerssoft.ims.security;

import com.flyerssoft.ims.model.entity.Employee;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Custom user details implementation for spring security.
 */

public class User extends Employee implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return super.getGroupPermissions().stream()
        .flatMap(userGroupPermission -> userGroupPermission.getEntitlements().stream())
        .map(entitlement -> new SimpleGrantedAuthority(
            entitlement.getName())).collect(Collectors.toSet());
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return String.valueOf(super.getFlyerssoftId());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
