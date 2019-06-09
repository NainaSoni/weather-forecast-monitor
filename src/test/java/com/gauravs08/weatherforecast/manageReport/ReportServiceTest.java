package com.gauravs08.weatherforecast.manageReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.gauravs08.weatherforecast.connectService.FileLocationBasedApiCallService;
import com.gauravs08.weatherforecast.locationManager.model.Location;
import com.gauravs08.weatherforecast.locationManager.model.Temperature;
import com.gauravs08.weatherforecast.locationManager.service.LocationService;
import com.gauravs08.weatherforecast.reportManager.model.Report;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;
import com.gauravs08.weatherforecast.reportManager.service.ReportService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReportServiceTest {

	
	@Autowired
	private ReportService reportService;
	 
	@Autowired
		@Value("${service.input-location-file}")
	private String locationFilePath ;

		Resource resource; 
		LocationService location;
		
		Set<Location> locationSet;
		
		@Autowired
		FileLocationBasedApiCallService srv ;
		Set<Location> actualLocationSet;
		Set<Report> reports;
		ReportMaster executedReportMaster;
		
	@Before 
	public void getLocationSetTest(){
		
		actualLocationSet = new HashSet<>();
		actualLocationSet.add(
				Location.builder()
				.city("Pune")
				.temp(Temperature.builder().max(20).min(10).build())
				.build());
		
		//locationSet = location.getAllLocationDetails();
		assertNotNull(actualLocationSet);	
	}
	
	@Test
	public void createReportsetFromLocationsetTest() throws Exception {
		
		//srv =  new FileLocationBasedApiCallService(locationService, owmService, converterService, reportService);
		
		reports = srv.createReportsetFromLocationset(actualLocationSet);
	
		assertNotNull(reports);
		Optional<Report> executedResult = reports.stream().findFirst();
	
		assertThat(executedResult.get().getLoc()).isEqualTo(actualLocationSet.stream().findFirst().get());
	
		
	}
	@Test
	public void createReportMasterTest() throws Exception{
		if(null==reports) {
			createReportsetFromLocationsetTest();
		}
		executedReportMaster = reportService.createReportMaster(reports);
		assertNotNull(executedReportMaster);
		Set<Report> executedResult = executedReportMaster.getReport();
		assertThat(executedResult).isEqualTo(reports);
		
	}
	
	@Test
	public void getReportTest() throws Exception{
		if(null==executedReportMaster) {
			createReportMasterTest();
		}
		assertNotNull(executedReportMaster);
	}
	

	
}
