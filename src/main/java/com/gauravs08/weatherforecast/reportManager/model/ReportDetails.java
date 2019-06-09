package com.gauravs08.weatherforecast.reportManager.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDetails {

	private final LocalDateTime timestamp;
	private final double temperature;
	
	private final boolean isMinTempReached;
	private final boolean isMaxTempReached;
}
