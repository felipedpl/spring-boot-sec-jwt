package br.com.felipedpl.secjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.felipedpl.secjwt.service.AuthenticationService;

@Component
public class TokenProvider implements AuthenticationProvider {

	@Autowired
	private AuthenticationService authService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("Verificando usuário e senha....");
		boolean loginSuccess = authService.login(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
		
		if (loginSuccess)
			return new UsernamePasswordAuthenticationToken(authentication.getPrincipal().toString(), null);
			
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
