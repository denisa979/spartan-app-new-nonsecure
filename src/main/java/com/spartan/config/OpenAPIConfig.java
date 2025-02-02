package com.spartan.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        title = "Spartan Rest API",
        version = "1.0",
        description = "An API with simple CRUD Operations to practice Rest API",
        contact = @Contact(name = "Adam", email = "adam@cydeo.com"))
)
public class OpenAPIConfig {
}
