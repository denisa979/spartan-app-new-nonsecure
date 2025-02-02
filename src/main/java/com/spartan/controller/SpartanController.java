package com.spartan.controller;

import com.spartan.dto.SpartanDTO;
import com.spartan.dto.SpartanPartialImplDTO;
import com.spartan.dto.wrapper.ExceptionWrapper;
import com.spartan.dto.wrapper.ResponseWrapper;
import com.spartan.service.SpartanService;
import com.spartan.util.SwaggerExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/spartans")
@Tag(name = "Spartan API", description = "CRUD Operations for Spartans")
public class SpartanController {

    private final SpartanService spartanService;

    public SpartanController(SpartanService spartanService) {
        this.spartanService = spartanService;
    }

    @PostMapping
    @Operation(summary = "Create a new Spartan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the Spartan.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.POST_SPARTAN_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_VALIDATION_SPARTAN))),
            @ApiResponse(responseCode = "409", description = "Spartan with this ID number already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_CREATE_SPARTAN))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> createSpartan(@Valid @RequestBody SpartanDTO spartanDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseWrapper("Successfully created the Spartan.", spartanService.createSpartan(spartanDTO)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Spartan based on the ID number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Spartan.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GET_SINGLE_SPARTAN_RESPONSE))),
            @ApiResponse(responseCode = "404", description = "Spartan with the given ID number could not be found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_GET_SINGLE_SPARTAN))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> getSpartanByUuid(@Parameter(description = "ID number of the Spartan to retrieve.", required = true) @PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                new ResponseWrapper("Successfully retrieved the Spartan.", spartanService.readSpartanByUuid(uuid)));
    }

    @GetMapping
    @Operation(summary = "Find all Spartans.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all the Spartans.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GET_MULTIPLE_SPARTANS_RESPONSE))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> getAllSpartans() {
        return ResponseEntity.ok(
                new ResponseWrapper("Successfully retrieved all the Spartans.", spartanService.readAllSpartans()));
    }

    @Operation(summary = "Search for Spartans.")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully queried all the Spartans.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.SEARCH_MULTIPLE_SPARTANS_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_VALIDATION_SEARCH_SPARTAN))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> getAllSpartansBasedOnSearchCriteria(
            @Parameter(description = "Name contains") @RequestParam(required = false) String nameContains,
            @Parameter(description = "Gender (Male/Female)") @RequestParam(required = false) String gender) {
        return ResponseEntity.ok(new ResponseWrapper("Successfully queried all the Spartans.",
                spartanService.readAllSpartansBasedOnSearchCriteria(nameContains, gender)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Spartan.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the Spartan.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.PUT_PATCH_SPARTAN_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_VALIDATION_SPARTAN))),
            @ApiResponse(responseCode = "404", description = "Spartan with the given ID number could not be found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_GET_SINGLE_SPARTAN))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> updateSpartan(@Valid @RequestBody SpartanDTO spartanDTO,
                                                         @Parameter(description = "ID number of the Spartan to update.", required = true) @PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                new ResponseWrapper("Successfully updated the Spartan.", spartanService.updateSpartan(spartanDTO, uuid)));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a Spartan partially.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpartanPartialImplDTO.class),
                            examples = {@ExampleObject(value = SwaggerExamples.PATCH_SPARTAN_REQUEST)})))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the Spartan.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.PUT_PATCH_SPARTAN_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "Invalid Input(s)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_VALIDATION_SPARTAN))),
            @ApiResponse(responseCode = "404", description = "Spartan with the given ID number could not be found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_GET_SINGLE_SPARTAN))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> updateSpartanPartially(@RequestBody Map<String, Object> fieldsToUpdate,
                                                                  @Parameter(description = "ID number of the Spartan to update.", required = true) @PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(
                new ResponseWrapper("Successfully updated the Spartan.", spartanService.updateSpartanPartially(uuid, fieldsToUpdate)));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Spartan based on the ID number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the Spartan.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.DELETE_SPARTAN_RESPONSE))),
            @ApiResponse(responseCode = "404", description = "Spartan with the given ID number could not be found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_GET_SINGLE_SPARTAN))),
            @ApiResponse(responseCode = "409", description = "Spartan with this ID number can not be deleted/already deleted.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.ERROR_RESPONSE_DELETE_SPARTAN))),
            @ApiResponse(responseCode = "500", description = "Action failed: An error occurred!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionWrapper.class),
                            examples = @ExampleObject(value = SwaggerExamples.GENERIC_ERROR_RESPONSE_JSON)))})
    public ResponseEntity<ResponseWrapper> deleteSpartanByUuid(@Parameter(description = "ID number of the Spartan to delete.", required = true) @PathVariable("id") UUID uuid) {
        spartanService.deleteSpartanByUuid(uuid);
        return ResponseEntity.ok(
                new ResponseWrapper("Successfully deleted the Spartan."));
    }

}
