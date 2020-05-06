package com.tabit.dcm2.service.impl;

import io.jsonwebtoken.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenServiceTest {
    private static final String SECRET = "mySecret";

    @Mock
    private JwtsFactory jwtsFactory;
    @Mock
    private JwtBuilder jwtBuilder;
    @Mock
    private JwtParser jwtParser;
    @Mock
    private Claims claims;
    @Mock
    private Jws<Claims> jws;
    @Captor
    private ArgumentCaptor<Date> dateArgumentCaptor;
    @InjectMocks
    private JwtTokenService jwtTokenService;

    @Test
    public void generateToken_should_generate_token() {
        // given
        String login = "login";
        String token = "token";
        when(jwtsFactory.createJwtBuilder()).thenReturn(jwtBuilder);
        when(jwtBuilder.setClaims(new HashMap<>())).thenReturn(jwtBuilder);
        when(jwtBuilder.setSubject(login)).thenReturn(jwtBuilder);
        when(jwtBuilder.setIssuedAt(dateArgumentCaptor.capture())).thenReturn(jwtBuilder);
        when(jwtBuilder.setExpiration(dateArgumentCaptor.capture())).thenReturn(jwtBuilder);
        when(jwtBuilder.signWith(SignatureAlgorithm.HS512, "mySecret")).thenReturn(jwtBuilder);
        when(jwtBuilder.compact()).thenReturn(token);

        // when
        String actualToken = jwtTokenService.generateToken(login);

        // then
        assertThat(actualToken).isEqualTo(token);
    }

    @Test
    public void getUsernameFromToken_should_get_username() {
        // given
        String token = "token";
        String username = "name";

        setupMockJwtParser(token);
        when(claims.getSubject()).thenReturn(username);

        // when
        String usernameFromToken = jwtTokenService.getUsernameFromToken(token);

        // then
        assertThat(usernameFromToken).isEqualTo(username);
    }

    @Test
    public void validateToken_should_return_true_if_not_expired() {
        // given
        String token = "token";
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());

        setupMockJwtParser(token);
        when(claims.getExpiration()).thenReturn(expiredDate);

        // when
        Optional<Boolean> usernameFromToken = jwtTokenService.validateToken(token);

        // then
        assertThat(usernameFromToken).contains(true);
    }

    @Test
    public void validateToken_should_return_absent_if_expired() {
        // given
        String token = "token";
        Date expiredDate = Date.from(LocalDateTime.now().minusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());

        setupMockJwtParser(token);
        when(claims.getExpiration()).thenReturn(expiredDate);

        // when
        Optional<Boolean> usernameFromToken = jwtTokenService.validateToken(token);

        // then
        assertThat(usernameFromToken).isEmpty();
    }

    private void setupMockJwtParser(String token) {
        when(jwtsFactory.createParser()).thenReturn(jwtParser);
        when(jwtParser.setSigningKey(SECRET)).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(token)).thenReturn(jws);
        when(jws.getBody()).thenReturn(claims);
    }
}