package com.example.porjectofinalpostgre;

import com.example.porjectofinalpostgre.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class PorjectoFinalPostgreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PorjectoFinalPostgreApplication.class, args);
	}

}
