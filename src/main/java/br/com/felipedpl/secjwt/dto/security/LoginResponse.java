package br.com.felipedpl.secjwt.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

	private String accessToken;
}
