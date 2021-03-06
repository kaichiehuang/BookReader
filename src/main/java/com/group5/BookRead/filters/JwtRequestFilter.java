package com.group5.BookRead.filters;

import com.group5.BookRead.services.user.MyUserDetailsService;
import com.group5.BookRead.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;


import javax.servlet.http.Cookie;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    public static final int TOKENPOS = 7;
    /**
     * <p> JWT Filter
     * </p>
     * @param filterChain
     * @param httpServletRequest
     * @param httpServletResponse
     * @since 1.0
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest
                                                httpServletRequest,
                                    final HttpServletResponse
                                            httpServletResponse,
                                    final FilterChain
                                                filterChain)
            throws ServletException, IOException {

        Cookie jwtCookie = WebUtils.getCookie(httpServletRequest, "jwt");

        final String authorizationHeader =
                httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (jwtCookie != null) {
            System.out.println(jwtCookie.getValue());
            jwt = jwtCookie.getValue();
        } else if (authorizationHeader != null
             && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(TOKENPOS);
        }

        if (jwt != null) {
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null
                && SecurityContextHolder.getContext().getAuthentication()
                == null) {
            UserDetails userDetails
                    = this.userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken
                        usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
