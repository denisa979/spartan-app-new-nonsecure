package com.spartan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spartan.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpartanPartialImplDTO {

    @Schema(example = "Leonidas",
            description = "The name of a Spartan. It cannot be empty/blank, if provided.",
            nullable = true,
            implementation = String.class)
    private String name;

    @Schema(example = "Male", description = "The gender of a Spartan. It can only be Male or Female, if provided.",
            nullable = true,
            implementation = Gender.class)
    private Gender gender;

    @Schema(example = "1234567890", description = "The phone number of a Spartan. It should be 10 to 13 characters long, and can only have numbers.", nullable = true)
    private String phone;

}
