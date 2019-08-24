package com.weatherapi.model.wb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Weather {

    @JsonProperty("icon")
    private String icon;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
}
