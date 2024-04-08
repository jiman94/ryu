package com.msa.template.inventory.config.web;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	/**
	 * localhost:8080/swagger-ui.html
	 */
	@Bean
	public OpenAPI api() {

		return new OpenAPI()
			.info(new Info()
				.title("메가트리 Front API")
				.description("메가트리 Front API 명세서(스펙)")
				.version("1.0.0"))
			.components(new Components()
//				.addSecuritySchemes("bearer-key",
//					new io.swagger.v3.oas.models.security.SecurityScheme()
//						.type(SecurityScheme.Type.HTTP)
//						.scheme("bearer")
//						.bearerFormat("JWT"))
			);
	}
}
