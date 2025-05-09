package com.PhoneX.Backend.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserPrincipal implements UserDetails {
    private final User user;
    public UserPrincipal(User user){
        this.user=user;
    }

    /*
    GrantedAuthority is an interface that represents an authority granted to the user.
    In Spring Security, roles and permissions are represented as GrantedAuthority objects.

    SimpleGrantedAuthority is a concrete implementation of GrantedAuthority.
    It represents a role as a simple String, like "USER" or "ADMIN"
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));//Collections.singleton(...) creates an immutable set containing exactly one element.
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        if (user.getCustomer() != null) {
            return user.getCustomer().getUsername();
        } else if (user.getAdmin() != null) {
            return user.getAdmin().getAdminname();
        }
        return null; // or throw an exception if neither is present
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
