package com.sushorg.serverlogsprocessor.database.mapper;

import com.sushorg.serverlogsprocessor.constants.LogsProcessorConstants;
import com.sushorg.serverlogsprocessor.dto.EventDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<EventDTO> {
    @Override
    public EventDTO mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(resultSet.getString("ID"));
        eventDTO.setDuration(resultSet.getLong("DURATION"));
        eventDTO.setLogType(resultSet.getString("LOG_TYPE"));
        eventDTO.setHost(resultSet.getString("HOST"));
        eventDTO.setAlert(resultSet.getString("ALERT"));
        return eventDTO;
    }
}
