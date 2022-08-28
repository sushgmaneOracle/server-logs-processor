package com.sushorg.serverlogsprocessor.services.impl;

import com.sushorg.serverlogsprocessor.constants.LogsProcessorConstants;
import com.sushorg.serverlogsprocessor.dto.EventDTO;
import com.sushorg.serverlogsprocessor.dto.LogRecordDTO;
import com.sushorg.serverlogsprocessor.repository.EventDAO;
import com.sushorg.serverlogsprocessor.services.LogFileReaderService;
import com.sushorg.serverlogsprocessor.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class LogFileReaderServiceImpl implements LogFileReaderService {

    @Autowired
    EventDAO eventDAO;
    ConcurrentHashMap<String, LogRecordDTO> logStateMap = new ConcurrentHashMap<>();

    @Override
    public boolean processLogFile(String filePath) {
        long start = System.currentTimeMillis();
        try {
            Files.lines(Paths.get(filePath)).parallel().map(JsonUtil::jsonToObjectMapper).forEach(
                    s -> {
                        EventDTO eventDTO = calculateDurationAndAlertFlag(s);
                        if(eventDTO != null ){
                            eventDAO.saveEvent(eventDTO);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        log.info("Total time required to execute (milliseconds) : "+(end-start));
        return true;
    }

    private EventDTO calculateDurationAndAlertFlag(Optional<LogRecordDTO> logRecordObject)  {

        try{
            //check if even is already present into Map
            if( logStateMap.get(logRecordObject.get().getId()) == null) {
                logStateMap.put(logRecordObject.get().getId(), logRecordObject.get());
                return null;
            }else{
                //if even is already present into Map then compare end and start time for duration
                long end=0;
                long start=0;
                LogRecordDTO logRecordObjectOld = logStateMap.get(logRecordObject.get().getId());
                if(logRecordObjectOld.getState() != null && logRecordObjectOld.getState().trim().equals(LogsProcessorConstants.LOG_STATE_FINISHED)){
                    end=logRecordObjectOld.getTimestamp();
                }else if(logRecordObjectOld.getState() != null && logRecordObjectOld.getState().trim().equals(LogsProcessorConstants.LOG_STATE_STARTED)){
                    start = logRecordObjectOld.getTimestamp();
                }
                if(logRecordObject.get().getState() != null && logRecordObject.get().getState().trim().equals(LogsProcessorConstants.LOG_STATE_FINISHED)){
                    end=logRecordObject.get().getTimestamp();
                }else if(logRecordObject.get().getState() != null && logRecordObject.get().getState().trim().equals(LogsProcessorConstants.LOG_STATE_STARTED)){
                    start = logRecordObject.get().getTimestamp();
                }
                if(end ==0 || start ==0){
                    return null;
                }else{
                    EventDTO eventDTO = new EventDTO();
                    long duration = end-start;
                    eventDTO.setId(logRecordObject.get().getId());
                    eventDTO.setDuration(end-start);
                    eventDTO.setLogType(logRecordObject.get().getType());
                    eventDTO.setHost(logRecordObject.get().getHost());
                    if(duration > LogsProcessorConstants.EVENT_LONGER_THRESHOLD){
                        eventDTO.setAlert("true");
                    }else{
                        eventDTO.setAlert("false");
                    }
                    //Clear Map once duration is calculated
                    logStateMap.remove(logRecordObject.get().getId());
                    return eventDTO;
                }
            }
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return null;
    }
}
