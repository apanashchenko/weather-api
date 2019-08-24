package com.weatherapi.model.owm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wind {

    @JsonProperty("speed")
    private Integer speed;
    @JsonProperty("deg")
    private Integer deg;
}
