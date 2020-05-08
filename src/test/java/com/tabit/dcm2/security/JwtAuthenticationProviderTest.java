package com.tabit.dcm2.security;

import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.exception.JwtAuthenticationException;
import com.tabit.dcm2.service.impl.JwtTokenService;
import com.tabit.dcm2.service.impl.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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

    @Mock
    private UserService userService;

    @InjectMocks
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Test
    public void authenticate_shall_return_authentication() {
        // given
        String login = "login";
        String token = "testToken";
        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
        User randomUser = RandomUser.createRandomUser();
        when(jwtTokenService.getUsernameFromToken(token)).thenReturn(login);
        when(userService.findByLogin(login)).thenReturn(randomUser);

        // when
        JwtAuthenticatedProfile jwtAuthenticatedProfile = (JwtAuthenticatedProfile) jwtAuthenticationProvider.authenticate(jwtAuthentication);

        // then
        assertThat(jwtAuthenticatedProfile.getPrincipal()).isEqualTo(randomUser);
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

    @Test(expected = JwtAuthenticationException.class)
    public void getUsernameFromToken_should_throw_exception_with_invalid_token() {
        // given
        String login = "login";
        String token = "testToken";
        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
        when(jwtTokenService.getUsernameFromToken(token)).thenThrow(new JwtException("test"));

        // when
        jwtAuthenticationProvider.authenticate(jwtAuthentication);
    }
}
