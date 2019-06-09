package com.gauravs08.weatherforecast.connectService;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gauravs08.weatherforecast.api.openweathermapforecast.model.ForecastOWM;
import com.gauravs08.weatherforecast.api.openweathermapforecast.service.OWMService;
import com.gauravs08.weatherforecast.locationManager.model.Location;
import com.gauravs08.weatherforecast.locationManager.service.LocationService;
import com.gauravs08.weatherforecast.reportManager.model.Report;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;
import com.gauravs08.weatherforecast.reportManager.service.ReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Periodically generate the report on given input JSON having city & temperature limits  and save the output in JSON format.
 * @param locationService
 * @param owmService
 * @param converterService
 * @param reportService
 * 
 * @author Gaurav Sharma
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Component
@Repository
public class FileLocationBasedApiCallService {
	@Autowired
	private LocationService locationService; 
	
	@Autowired
	private OWMService owmService;
	
	@Autowired
	private  ForecastOWMToReportMasterConvert converterService;
	
	@Autowired 
	private ReportService reportService;
	
	
	public FileLocationBasedApiCallService(LocationService locationService, OWMService owmService,
			ForecastOWMToReportMasterConvert converterService, ReportService reportService) {
		super();
		this.locationService = locationService;
		this.owmService = owmService;
		this.converterService = converterService;
		this.reportService = reportService;
	}
	
	/**Scheduler to generate report for every refresh cycle
	 * @return void
	 */
	@Scheduled(fixedDelayString = "${service.refresh-rate}")
    public void generateReport() {
        try {
        	startProcessing();
        } catch (Exception e) {
            LOGGER.error("Generate Report Failed with error. ", e);
        }
    }
	
	
	
	/** Four task are executed as below
	 * 1. Read input json file and store in Location set
	 * 2. For each location from above, generate Report set.
	 * 3. Assemble final Report master with timestamp from above Report set
	 * 4. Save the Report Master into output json file.
	 */
	private void startProcessing() {
		//1. Get all location from JSON input file
			Set<Location> locationSet = locationService.getAllLocationDetails();
		
		//2. For each locationSet get the Report and save in Set<Report>
			Set<Report> reportSet = createReportsetFromLocationset(locationSet);
			
		
		//3.Generate final RepostMaster from Set<Report>
			ReportMaster reportMaster = reportService.createReportMaster(reportSet);
		
		//4. Save to output file
			reportService.saveReportMaster(reportMaster);
	}
	
	
	 /** Create Report set from location set 
	 * @param locationSet
	 * @return set of Report
	 */
	public Set<Report> createReportsetFromLocationset(Set<Location> locationSet) {
	        return locationSet.stream()
	                            .map(this::createReportFromLocation)
	                            .collect(Collectors.toSet());
	    }
	 public Report createReportFromLocation(Location location) {
	    	ForecastOWM forecast = owmService.getForecastForLocation(location.getCity());
	    	return converterService.convertForestOWMToReport(forecast,location);
	    }
	 
}
