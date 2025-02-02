package com.spartan.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationExceptionWrapper {

    @Schema(nullable = true)
    private String errorField;

    @Schema(nullable = true)
    private Object rejectedValue;

    @Schema(nullable = true)
    private String reason;

}
