package br.com.felipedpl.secjwt.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginCredentials {

	private String username;
	private String password;
}
