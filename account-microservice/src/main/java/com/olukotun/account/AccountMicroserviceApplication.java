package com.olukotun.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "Olukotun bank Account Microservice Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Olukotun Oludamilare",
                        email = "olukotuno@gmail.com",
                        url="https://www.facebook.com/olukotun.oludamilare"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.facebook.com/olukotun.oludamilare"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Olukotun banking account microservice",
                url = ""
        )
)
public class AccountMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroserviceApplication.class, args);
    }

}
