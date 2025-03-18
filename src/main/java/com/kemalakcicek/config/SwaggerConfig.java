package com.kemalakcicek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	//Bu metot sayesinde authorization butonu oluşmasını sağladık
	@Bean
	public OpenAPI customeOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
				.title("Swagger Api's")
				.description("Projedeki apilar"))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new Components().addSecuritySchemes("bearerAuth", 
						new SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer") //Token başına jwt ekle demek
						.bearerFormat("JWT")
						.description("JWT Token değerini giriniz")));
		
	}

}
