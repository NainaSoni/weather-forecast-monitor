package com.gauravs08.weatherforecast.reportManager.model;

import java.util.List;

import com.gauravs08.weatherforecast.locationManager.model.Location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {
	private final Location loc;
	
	private final List<ReportDetails> reportDetails;
	
	
}
 