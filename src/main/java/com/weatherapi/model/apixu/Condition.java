package com.weatherapi.model.apixu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Condition {

    @JsonProperty("text")
    private String text;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("code")
    private Integer code;
}
