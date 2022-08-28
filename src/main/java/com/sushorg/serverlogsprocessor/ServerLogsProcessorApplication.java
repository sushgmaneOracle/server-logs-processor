package com.sushorg.serverlogsprocessor;

import com.sushorg.serverlogsprocessor.services.LogFileReaderService;
import com.sushorg.serverlogsprocessor.services.impl.LogFileReaderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ServerLogsProcessorApplication implements ApplicationRunner {

	@Value("${file.path}")
	private String filePath;

	@Autowired
	LogFileReaderService logFileReaderService;

	public static void main(String[] args) {
		SpringApplication.run(ServerLogsProcessorApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args)  {
		if(filePath == null || filePath.equals("")){
			log.info("No file available for processing....");
		}else{
			log.info("File ["+filePath+"] found ....");
			logFileReaderService.processLogFile(filePath);
		}
	}
}
