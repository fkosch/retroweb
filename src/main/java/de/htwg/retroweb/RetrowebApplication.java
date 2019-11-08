package de.htwg.retroweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RetrowebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RetrowebApplication.class, args);//comment
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RetrowebApplication.class);
	}
}
