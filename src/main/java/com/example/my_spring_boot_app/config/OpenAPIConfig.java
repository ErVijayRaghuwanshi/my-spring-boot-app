package com.example.my_spring_boot_app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Student Management API",
        version = "1.0.0",
        description = "This API provides endpoints for managing student data, including creating, retrieving, updating, and deleting records.",
        contact = @Contact(
            name = "API Support",
            email = "ervijayraghuwanshi@gmail.com",
            url = "https://ervijayraghuwanshi.github.io/ErVijayRaghuwanshi"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    servers = {
        @Server(url = "/", description = "Default Server URL")
    }
)
@Configuration
public class OpenAPIConfig {

}