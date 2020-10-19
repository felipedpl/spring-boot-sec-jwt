package br.com.felipedpl.secjwt.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.felipedpl.secjwt.dto.security.LoginCredentials;
import br.com.felipedpl.secjwt.dto.security.LoginResponse;
import br.com.felipedpl.secjwt.service.TokenAuthenticationService;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println("Tentando autenticar...");
		LoginCredentials login = new ObjectMapper().readValue(request.getInputStream(), LoginCredentials.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), Collections.emptyList()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// add token to header
		System.out.println("Autenticado com sucesso");
		String token = TokenAuthenticationService.getToken(authResult.getPrincipal().toString());
		LoginResponse resp = new LoginResponse();
		resp.setAccessToken(token);
		ObjectMapper mapper = new ObjectMapper();
		response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		mapper.writeValue(response.getOutputStream(), resp);
	}

}
