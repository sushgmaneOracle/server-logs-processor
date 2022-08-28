package com.sushorg.serverlogsprocessor.dto;

import lombok.Data;

@Data
public class EventDTO {
    private String id;
    private long duration;
    private String host;
    private String logType;
    private String alert;
}
