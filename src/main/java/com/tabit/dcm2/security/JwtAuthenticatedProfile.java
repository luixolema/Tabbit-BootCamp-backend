package com.tabit.dcm2.security;

import com.tabit.dcm2.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthenticatedProfile implements Authentication {
    private static final long serialVersionUID = 1L;
    private final User user;

    public JwtAuthenticatedProfile(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {

    }

    @Override
    public String getName() {
        return user.getLogin();
    }
}
