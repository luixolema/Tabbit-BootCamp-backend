package com.tabit.dcm2.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final String TOKEN_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestHeader = request.getHeader(TOKEN_HEADER);

        if (requestHeader != null && requestHeader.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication() == null) {
            String authToken = requestHeader.substring(7);
            JwtAuthentication authentication = new JwtAuthentication(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
