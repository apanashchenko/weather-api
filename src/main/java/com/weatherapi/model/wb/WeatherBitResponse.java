package com.weatherapi.model.wb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherBitResponse {

    @JsonProperty("data")
    private List<Datum> data = null;
    @JsonProperty("count")
    private Integer count;
}
