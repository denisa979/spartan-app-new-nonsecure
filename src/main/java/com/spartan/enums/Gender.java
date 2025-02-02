package com.spartan.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum Gender {

    @JsonProperty("Male")
    MALE("Male"),
    @JsonProperty("Female")
    FEMALE("Female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

}
