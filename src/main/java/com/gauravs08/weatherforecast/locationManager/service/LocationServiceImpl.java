package com.gauravs08.weatherforecast.locationManager.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravs08.weatherforecast.exception.ResourceNotFoundException;
import com.gauravs08.weatherforecast.locationManager.model.Location;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LocationServiceImpl implements LocationService{

		@Autowired
		private final ObjectMapper objectMapper;

		@Autowired
	    private final ResourceLoader resourceLoader;

		@Autowired
		@Value("${service.input-location-file}")
	    private final String locationFilePath;
	    
	    public LocationServiceImpl(@Autowired ObjectMapper objectMapper,
                @Autowired ResourceLoader resourceLoader,
                @Value("${service.input-location-file}") String locationFilePath) {
	    	this.objectMapper = objectMapper;
	    	this.resourceLoader = resourceLoader;
	    	this.locationFilePath = locationFilePath;
	    }
	    
	/** Get all Location Details for each location loaded from input location path
	 * @return Set of Location
	 *
	 */
	@Override
	public Set<Location> getAllLocationDetails() {
	
		 Resource resource = resourceLoader.getResource(locationFilePath);
	        try {
	            return Arrays.stream(objectMapper.readValue(resource.getInputStream(), Location[].class))
	                         .collect(Collectors.toSet());
	        } catch (IOException e) {
	        	LOGGER.error("Resource not found at location ",locationFilePath);
	            throw new ResourceNotFoundException(locationFilePath);
	        }
	}

}
