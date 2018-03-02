package com.timeclock.web.ClockBeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ClockBetaApplication {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/rest/**").allowedOrigins("http://localhost:3000, https://spring-clock-ui.herokuapp.com");
				registry.addMapping("/login").allowedOrigins("http://localhost:3000, https://spring-clock-ui.herokuapp.com");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClockBetaApplication.class, args);
	}
}
