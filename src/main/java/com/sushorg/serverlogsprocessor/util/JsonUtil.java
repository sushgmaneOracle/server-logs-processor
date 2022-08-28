package com.sushorg.serverlogsprocessor.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushorg.serverlogsprocessor.dto.LogRecordDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class JsonUtil {
    public static Optional<LogRecordDTO> jsonToObjectMapper(String logRecordJson){
        LogRecordDTO logRecordDTO = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logRecordDTO = objectMapper.readValue(logRecordJson, LogRecordDTO.class);
        } catch (JsonProcessingException e) {
            log.error("No JSON format "+e.getMessage());
        }
        return Optional.ofNullable(logRecordDTO);
    }
}
