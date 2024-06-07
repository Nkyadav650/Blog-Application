package com.G_Vichar.Blog.Config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(contact = @Contact(name = "NKYadav", email = "nkyadav650@gmail.com", url = "https://globalvichar.com"), description = "This project developed By NKyadav", title = "Global Vichar", version = "1.0.0", license = @License(name = "License of Api", url = "https://gvichar.com"), termsOfService = "Term of services"), servers = {
		@Server(description = "Local ENV", url = "http://localhost:3839/"),
		@Server(description = "PROD ENV", url = "https://globalvichar.com") }, security = {
				@SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

}
