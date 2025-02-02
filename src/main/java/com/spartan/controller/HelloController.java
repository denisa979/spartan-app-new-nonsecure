package com.spartan.controller;

import com.spartan.dto.HelloResponseDTO;
import com.spartan.dto.wrapper.ExceptionWrapper;
import com.spartan.util.SwaggerExamples;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/hello")
@Tag(name = "Hello API", description = "Hello Controller")
public class HelloController {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hello World!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HelloResponseDTO.class),
                            examples = @ExampleObject(value = SwaggerExamples.HELLO_WORLD_JSON_RESPONSE)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = HelloResponseDTO.class),
                                    examples = @ExampleObject(value = SwaggerExamples.HELLO_WORLD_XML_RESPONSE))}),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ExceptionWrapper.class),
                                    examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_XML))})})
    public HelloResponseDTO home() {

        HelloResponseDTO response = new HelloResponseDTO();
        response.setMessage("Hello World!");

        return response;
    }

}
