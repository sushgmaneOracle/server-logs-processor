package com.sushorg.serverlogsprocessor.repository;

import com.sushorg.serverlogsprocessor.constants.LogsProcessorConstants;
import com.sushorg.serverlogsprocessor.dto.EventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class EventDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveEvent(EventDTO eventDTO) {
        try{
            jdbcTemplate.update(LogsProcessorConstants.SQL_NEW_EVENT_QUERY, new Object[] { eventDTO.getId(), eventDTO.getDuration(),
                    eventDTO.getHost(), eventDTO.getLogType(), eventDTO.getAlert()});
            log.info("Saved Event ["+eventDTO+"] into database");
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }
}
