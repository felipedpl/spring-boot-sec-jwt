package br.com.felipedpl.secjwt.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	public boolean login(String username, String password) {
		return username.equalsIgnoreCase("admin") && password.equals("Admin123@");
	}
}
