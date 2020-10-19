package br.com.felipedpl.secjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class SpringBootSecJwtNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecJwtNewApplication.class, args);
	}

	@GetMapping
	public String helloWorld() {
		return "Hello world";
	}
}
