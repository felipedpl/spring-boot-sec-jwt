package br.com.felipedpl.secjwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import br.com.felipedpl.secjwt.service.TokenAuthenticationService;

public class JWTAuthenticationFilter extends GenericFilterBean {

	public static final String HEADER_STRING = "Authorization"; 

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// verifica se há um token na requisição
		System.out.println("Verificando se há token na requisição");
		HttpServletRequest req = (HttpServletRequest) request;
		Authentication authentication = TokenAuthenticationService.getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
