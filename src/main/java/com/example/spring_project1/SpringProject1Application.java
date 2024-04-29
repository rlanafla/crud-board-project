package com.example.spring_project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//listener 활성화 위함
public class SpringProject1Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringProject1Application.class, args);
	}

}
