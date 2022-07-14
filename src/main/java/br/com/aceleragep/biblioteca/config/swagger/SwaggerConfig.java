package br.com.aceleragep.biblioteca.config.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("br.com.aceleragep").pathsToMatch("/**").build();
	}

	@Bean
	public OpenAPI bibliotecaOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Biblioteca API").description("Projeto API de biblioteca").version("v0.0.1"));
	}

}
