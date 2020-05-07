package com.tabit.dcm2.security;

import com.tabit.dcm2.exception.JwtAuthenticationException;
import com.tabit.dcm2.service.impl.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
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
        String token = "testToken";
        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
        when(jwtTokenService.getUsernameFromToken(token)).thenReturn(login);

        // when
        JwtAuthenticatedProfile jwtAuthenticatedProfile = (JwtAuthenticatedProfile) jwtAuthenticationProvider.authenticate(jwtAuthentication);

        // then
        assertThat(jwtAuthenticatedProfile.getPrincipal()).isEqualTo(login);
    }

    @Test(expected = JwtAuthenticationException.class)
    public void getUsernameFromToken_should_throw_exception() {
        // given
        String login = "login";
        String token = "testToken";
        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
        when(jwtTokenService.getUsernameFromToken(token)).thenThrow(new ExpiredJwtException(null, null, "expired"));

        // when
        jwtAuthenticationProvider.authenticate(jwtAuthentication);
    }
}
