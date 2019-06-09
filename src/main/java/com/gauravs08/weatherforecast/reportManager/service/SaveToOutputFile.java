package com.gauravs08.weatherforecast.reportManager.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SaveToOutputFile {

	 	private final ObjectMapper objectMapper;

	    private final String outputFilePath;

	    public SaveToOutputFile(@Autowired ObjectMapper objectMapper,
	                       @Value("${service.output-weather-report-file}") String filePath) {
	        this.objectMapper = objectMapper;
	        this.outputFilePath = filePath;
	    }

	 
	    /** Save ReportMaster to output file as Json
	     * @param reportMaster
	     * @return void
	     */
	    @EventListener
	    @Async
	    public void saveReportMaster(ReportMaster reportMaster) {

	        try {
	            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), reportMaster);
	        } catch (Exception e) {
	            LOGGER.error("Report failed to save, error:", e);
	        }
	    }
}
