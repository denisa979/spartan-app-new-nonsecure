package com.spartan.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spartan.dto.SpartanDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {

    @Schema(description = "The success or fail message that shows the result of the request.")
    private String message;

    @Schema(example = "1", description = "The total number of result data returned.", nullable = true)
    private Integer totalElement;

    @Schema(oneOf = {SpartanDTO.class, SpartanDTOList.class}, nullable = true)
    private Object data;

    public ResponseWrapper(String message, Object data) {
        this.message = message;
        this.totalElement = data == null ? 0 : data instanceof List<?> ? ((List<?>) data).size() : 1;
        this.data = data;
    }

    public ResponseWrapper(String message) {
        this.message = message;
    }

}
