package br.rafaelhorochovec.dragonball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiDragonballApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDragonballApplication.class, args);
	}

}
