//package com.example.movie3layer.login;
//
//import java.io.IOException;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//
//public class JWTAuthorizationFilter extends GenericFilterBean implements Filter {
//
//	@Override
//	public void doFilter(
//			jakarta.servlet.ServletRequest servletRequest,
//			jakarta.servlet.ServletResponse servletResponse,
//			jakarta.servlet.FilterChain filterChain)
//			throws IOException, jakarta.servlet.ServletException {
//		Authentication authentication = JWTTokenService.parseTokenToUserInformation((HttpServletRequest) servletRequest);
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		filterChain.doFilter(servletRequest, servletResponse);
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//	}
//}
//
