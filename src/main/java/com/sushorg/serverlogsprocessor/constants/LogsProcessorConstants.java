package com.sushorg.serverlogsprocessor.constants;

/**
 * @author Sushant Mane
 * Holds all the string constants used across the server logs processor.
 */
public final class LogsProcessorConstants {

    public static final String LOG_STATE_STARTED  = "STARTED";
    public static final String LOG_STATE_FINISHED  = "FINISHED";

    // This is threshold value to set alert true or false
    public static final int EVENT_LONGER_THRESHOLD  = 4000;

    //Database Queries
    public static final String SQL_NEW_EVENT_QUERY = "INSERT INTO LOG_EVENT(ID, DURATION, HOST, LOG_TYPE, ALERT) VALUES(?,?,?,?,?)";



}
