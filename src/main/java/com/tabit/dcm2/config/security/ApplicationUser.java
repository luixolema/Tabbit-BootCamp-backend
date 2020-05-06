package com.tabit.dcm2.config.security;

import com.tabit.dcm2.entity.DiveCenter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class ApplicationUser implements UserDetails {

    private final String username;
    private final String password;
    private final DiveCenter diveCenter;

    public ApplicationUser(String username, String password, DiveCenter diveCenter) {
        this.username = username;
        this.password = password;
        this.diveCenter = diveCenter;
    }

    public DiveCenter getDiveCenter() {
        return diveCenter;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
