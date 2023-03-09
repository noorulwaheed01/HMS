package com.example.FGPC_HMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditImp")
public class FgpcHmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FgpcHmsApplication.class, args);
	}

}
