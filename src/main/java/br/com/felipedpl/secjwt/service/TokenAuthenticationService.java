package br.com.felipedpl.secjwt.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	public static final long EXPIRATION_TIME = 860_000_000;
	public static final String SECRET = "MySecret";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization"; 

	public static String getToken(String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		return JWT;
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		try {
			String token = request.getHeader(HEADER_STRING);
			
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			if (user != null)
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
}
