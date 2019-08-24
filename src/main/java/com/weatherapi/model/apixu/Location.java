package com.weatherapi.model.apixu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("tz_id")
    private String tzId;
    @JsonProperty("localtime_epoch")
    private Integer localtimeEpoch;
    @JsonProperty("localtime")
    private String localtime;
}
