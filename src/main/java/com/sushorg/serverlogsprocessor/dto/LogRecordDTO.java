package com.sushorg.serverlogsprocessor.dto;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LogRecordDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("state")
    private String state;
    @JsonProperty("type")
    private String type;
    @JsonProperty("HOST")
    private String host;
    @JsonProperty("timestamp")
    private long timestamp;
}
