package com.inditex.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						name = "Wendy Motivar",
						email = "wendymotivar@gmail.com"
				),
				description = "Open API Description for Prices Service",
				title = "Open API Spec - Prices Service by Wendy Motivar",
				version = "1.0"
		),
		servers = {
				@Server(
						description = "Local ENV",
						url = "http://localhost:8080"
				)
		}
)
public class OpenApiConfig {
}
