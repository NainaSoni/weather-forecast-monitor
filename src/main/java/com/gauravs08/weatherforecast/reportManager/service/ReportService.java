package com.gauravs08.weatherforecast.reportManager.service;

import java.util.Set;

import com.gauravs08.weatherforecast.reportManager.model.Report;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;

public interface ReportService {
	 
	 void saveReportMaster(ReportMaster reportMaster);
	 
	 ReportMaster getReport();
	 
	 ReportMaster createReportMaster(Set<Report> reports); 
}
