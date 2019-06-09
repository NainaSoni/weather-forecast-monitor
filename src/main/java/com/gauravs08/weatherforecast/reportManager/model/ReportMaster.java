package com.gauravs08.weatherforecast.reportManager.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportMaster {
	private final LocalDateTime reportDate;
	 private final Set<Report> report;
}
