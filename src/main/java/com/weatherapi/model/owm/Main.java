package com.weatherapi.model.owm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main {

    @JsonProperty("temp")
    private Integer temp;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("temp_min")
    private Integer tempMin;
    @JsonProperty("temp_max")
    private Integer tempMax;
}
