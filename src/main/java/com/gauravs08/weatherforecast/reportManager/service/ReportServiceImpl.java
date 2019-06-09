package com.gauravs08.weatherforecast.reportManager.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gauravs08.weatherforecast.reportManager.model.Report;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	 
	 private  volatile ReportMaster reportMaster;
	 
	 @Autowired
	 private final SaveToOutputFile saveToOutputFile;
	  	  
	 /**Save the ReportMaster to Output file
	  * @param ReportMaster
	 *	@return void
	 */
	@Override
	 public void saveReportMaster(ReportMaster reportMaster) {
		 
	    this.reportMaster = reportMaster; 
		 
		
		saveToOutputFile.saveReportMaster(reportMaster);
		
	}
	
	 /** Create ReportMaster from set of Reports
	  * @param Set<Report>
	 *  @return ReportMaster
	 */
	@Override
	 public ReportMaster createReportMaster(Set<Report> reports) {
	        return ReportMaster.builder()
	                           .reportDate(LocalDateTime.now())
	                           .report(reports)
	                           .build();
	    }
	
	 /** Return ReportMaster 
	 *
	 */
	@Override
	 public ReportMaster getReport() {
		return reportMaster;
	}


}
