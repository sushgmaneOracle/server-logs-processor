package com.sushorg.serverlogsprocessor.services;

/**
 * @author Sushant Mane
 * This service contract will provide all the log file read related functionality
 * This service is responsible to read data from log files
 */
public interface LogFileReaderService {

    /**
     * Process log file records
     * @param filePath : file which needs to be process
     * @return boolean true if file process successfully else false
     */
    public boolean processLogFile(String filePath);
}
