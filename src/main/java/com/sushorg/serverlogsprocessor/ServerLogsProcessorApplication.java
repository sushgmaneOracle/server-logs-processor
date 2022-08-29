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

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class ServerLogsProcessorApplication implements ApplicationRunner {

	@Autowired
	LogFileReaderService logFileReaderService;

	public static void main(String[] args) {
		SpringApplication.run(ServerLogsProcessorApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args)  {

		String filePath = null;
		for (String name : args.getOptionNames()){
			log.info("argument is -> " + name + "=" + args.getOptionValues(name));
			if(name != null && name.trim().equals("file.path")){
				filePath = args.getOptionValues(name).get(0);
			}
		}
		if(filePath == null || filePath.equals("")){
			log.info("No file available for processing....");
		}else{
			log.info("File ["+filePath+"] found ....");
			logFileReaderService.processLogFile(filePath);
		}
	}
}
