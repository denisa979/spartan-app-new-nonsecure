package com.spartan.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionWrapper {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    @Schema(nullable = true)
    private Integer errorCount;

    @Schema(nullable = true)
    private List<ValidationExceptionWrapper> validationExceptions;

}
