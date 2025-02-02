package com.spartan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spartan.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "A Spartan object.")
public class SpartanDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(example = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
            description = "The ID number of a Spartan. It should not be inserted by the users.",
            implementation = UUID.class)
    private UUID uuid;

    @NotBlank(message = "The name is required.")
    @Schema(example = "Leonidas",
            description = "The name of a Spartan. It cannot be null.",
            implementation = String.class)
    private String name;

    @NotNull(message = "The gender is required, and can only have 'Male' or 'Female' values.")
    @Schema(example = "Male", description = "The gender of a Spartan. It cannot be null. It can only be Male or Female.",
            implementation = Gender.class)
    private Gender gender;

    @NotBlank(message = "The phone number is required.")
    @Pattern(regexp = "^\\d{10,13}$", message = "The phone number should be 10 to 13 characters long, and can only include digits.")
    @Schema(example = "1234567890", description = "The phone number of a Spartan. It should be 10 to 13 characters long, and can only have numbers.")
    private String phone;

}
