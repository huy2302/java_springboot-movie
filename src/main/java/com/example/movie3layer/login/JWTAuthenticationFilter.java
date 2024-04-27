//package com.example.movie3layer.login;
//
//import java.io.IOException;
//import java.util.Collections;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements Filter {
//
//    public JWTAuthenticationFilter(String url, AuthenticationManager authManager) {
//        super(new AntPathRequestMatcher(url));
//        setAuthenticationManager(authManager);
//    }
//    @Override
//    public Authentication attemptAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws AuthenticationException, IOException, jakarta.servlet.ServletException {
//        return getAuthenticationManager().authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getParameter("username"),
//                        request.getParameter("password"),
//                        Collections.emptyList()
//                )
//        );
//    }
//
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult) throws IOException, ServletException {
//        JWTTokenService.addJWTTokenToHeader(response, authResult.getName());
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//    }
//}
