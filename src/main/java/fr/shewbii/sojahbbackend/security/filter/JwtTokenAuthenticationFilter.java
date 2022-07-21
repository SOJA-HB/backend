package fr.shewbii.sojahbbackend.security.filter;

import fr.shewbii.sojahbbackend.security.helper.TokenHelper;
import fr.shewbii.sojahbbackend.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final TokenHelper tokenHelper;

    public JwtTokenAuthenticationFilter(UserDetailsServiceImpl userDetailsService, TokenHelper tokenHelper) {
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenHelper.getToken(request);

        if(!Objects.isNull(token)) {
            try {
                String username = tokenHelper.getUsernameFromToken(token);
                if(!Objects.isNull(username) && tokenHelper.isValidToken(token)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                    throw new UsernameNotFoundException("INVALID USERNAME");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            }
        }

        filterChain.doFilter(request, response);
    }
}
