package com.tabit.dcm2.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationTokenFilterTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;

    @Before
    public void setUp() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void doFilterInternal_should_filter_correct_if_Bearer_Header() throws ServletException, IOException {
        // given
        when(request.getHeader("Authorization")).thenReturn("Bearer blalbal");

        new JwtAuthenticationTokenFilter().doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication().getCredentials()).isEqualTo("blalbal");
    }

    @Test
    public void doFilterInternal_should_filter_correct_if_not_Bearer_Header() throws ServletException, IOException {
        // given
        when(request.getHeader("Authorization")).thenReturn(null);

        new JwtAuthenticationTokenFilter().doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}