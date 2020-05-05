package com.tabit.dcm2.security;

import com.tabit.dcm2.exception.JwtAuthenticationException;
import com.tabit.dcm2.service.impl.JwtTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationProviderTest {
    @Mock
    private JwtTokenService jwtTokenService;

    @InjectMocks
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Test
    public void authenticate_shall_return_authentication() {
        // given
        String login = "login";
        JwtAuthentication jwtAuthentication = new JwtAuthentication("testToken");
        when(jwtTokenService.getUsernameFromToken(anyString())).thenReturn(login);
        when(jwtTokenService.validateToken(anyString())).thenReturn(Optional.of(Boolean.TRUE));

        // when
        JwtAuthenticatedProfile jwtAuthenticatedProfile = (JwtAuthenticatedProfile) jwtAuthenticationProvider.authenticate(jwtAuthentication);

        // then
        assertThat(jwtAuthenticationProvider).isNotNull();
        assertThat(jwtAuthenticatedProfile.getPrincipal()).isEqualTo(login);
    }

    @Test(expected = JwtAuthenticationException.class)
    public void authenticate_shall_throw_exception() {
        // given
        String login = "login";
        JwtAuthentication jwtAuthentication = new JwtAuthentication("testToken");
        when(jwtTokenService.getUsernameFromToken(anyString())).thenReturn(login);
        when(jwtTokenService.validateToken(anyString())).thenReturn(Optional.empty());

        // when
        jwtAuthenticationProvider.authenticate(jwtAuthentication);
    }
}
