package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.commons.ApplicationProperties;
import io.jsonwebtoken.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenServiceTest {
    private static final String SECRET = "mySecret";
    private static final long EXPIRATION = 60000L;

    @Mock
    private ApplicationProperties applicationProperties;
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

    @Before
    public void setUp() {
        when(applicationProperties.getExpiration()).thenReturn(EXPIRATION);
        when(applicationProperties.getSecret()).thenReturn(SECRET);
    }

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
        when(jwtBuilder.signWith(SignatureAlgorithm.HS512, SECRET)).thenReturn(jwtBuilder);
        when(jwtBuilder.compact()).thenReturn(token);

        // when
        String actualToken = jwtTokenService.generateToken(login);

        // then
        assertThat(actualToken).isEqualTo(token);

        List<Date> allValues = dateArgumentCaptor.getAllValues();
        assertThat(allValues).hasSize(2);
        Date createDate = allValues.get(0);
        Date expiredDate = allValues.get(1);
        assertThat(expiredDate.getTime() - createDate.getTime()).isEqualTo(EXPIRATION);
    }

    @Test
    public void getUsernameFromToken_should_get_username() {
        // given
        String token = "token";
        String username = "name";

        when(jwtsFactory.createParser()).thenReturn(jwtParser);
        when(jwtParser.setSigningKey(SECRET)).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(token)).thenReturn(jws);
        when(jws.getBody()).thenReturn(claims);
        when(claims.getSubject()).thenReturn(username);

        // when
        String usernameFromToken = jwtTokenService.getUsernameFromToken(token);

        // then
        assertThat(usernameFromToken).isEqualTo(username);
    }


    @Test(expected = BadCredentialsException.class)
    public void getUsernameFromToken_should_throw_BadCredentialsException_with_invalid_token() {
        // given
        String token = "token";

        when(jwtsFactory.createParser()).thenReturn(jwtParser);
        when(jwtParser.setSigningKey(SECRET)).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(token)).thenThrow(new JwtException("test"));

        //when
        jwtTokenService.getUsernameFromToken(token);
    }
}