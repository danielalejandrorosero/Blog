package com.Blog.Personal.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = " Blog API",
                description = " Blog API Documentation"
        ),
        servers = {
                @Server(
                        description = " Blog API",
                        url = "http://localhost:8080/"
                ),

                @Server(
                        description = "Production Server",
                        url = "http://localhost:8080/Production"
                )
        }

)
public class SwaggerConfig {

}
