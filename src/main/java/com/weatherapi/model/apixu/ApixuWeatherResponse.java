package com.weatherapi.model.apixu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApixuWeatherResponse {

    @JsonProperty("location")
    private Location location;
    @JsonProperty("current")
    private Current current;

}
