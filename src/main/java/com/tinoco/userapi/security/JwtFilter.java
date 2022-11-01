package com.tinoco.userapi.security;

import com.tinoco.userapi.domain.service.UserDetailService;
import com.tinoco.userapi.error.AccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends GenericFilterBean {
    private static final String BEARER = "Bearer";

    private UserDetailService userDetailServiceServiceInt;

    public JwtFilter(UserDetailService userDetailServiceServiceInt) {
        this.userDetailServiceServiceInt = userDetailServiceServiceInt;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String headerValue = ((HttpServletRequest) request).getHeader("Authorization");
        Optional<String> bearerToken = getBearerToken(headerValue);
        bearerToken.ifPresent(token -> {
            userDetailServiceServiceInt.loadUserByJwtToken(token)
                    .ifPresentOrElse(userDetails -> {
                                SecurityContextHolder.getContext()
                                        .setAuthentication(new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
                            },
                            () ->
                                    new AccessException("Acceso Denegado")
                    );
        });


        chain.doFilter(request, response);
    }

    private Optional<String> getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }
        return Optional.empty();
    }

}