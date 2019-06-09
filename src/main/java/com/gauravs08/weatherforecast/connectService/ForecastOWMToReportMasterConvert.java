package com.gauravs08.weatherforecast.connectService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gauravs08.weatherforecast.api.openweathermapforecast.model.DailyWeatherRecord;
import com.gauravs08.weatherforecast.api.openweathermapforecast.model.ForecastOWM;
import com.gauravs08.weatherforecast.locationManager.model.Location;
import com.gauravs08.weatherforecast.reportManager.model.Report;
import com.gauravs08.weatherforecast.reportManager.model.ReportDetails;

/**
 * Conversion class  for ForecastOWM  to desired output as Report 
 * @author sharm
 *
 */
@Component
public class ForecastOWMToReportMasterConvert {
	
	 /** To Convert ForecastOWM to Report 
	 * @param forecastOWM
	 * @param location
	 * @return Report
	 */
	public Report convertForestOWMToReport(ForecastOWM forecastOWM,Location location) {
	        return Report.builder()
	        				.loc(location)
	                        .reportDetails(forecastOWM.getList()
	                                                 .stream()
	                                                 .map(dailyWeatherRecord -> convertDailyWeatherRecordToReportDetails(dailyWeatherRecord,location))
	                                                 .collect(Collectors.toList()))
	                       .build();

	    }
	 
	 /** Convert DailyWeatherRecord to ReportDetails
	 * @param dailyWeatherRecord
	 * @param location
	 * @return ReportDetails
	 */
	private ReportDetails convertDailyWeatherRecordToReportDetails(DailyWeatherRecord dailyWeatherRecord, Location location) {
		 
		 String str = dailyWeatherRecord.getDt_txt();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		 
		return ReportDetails.builder()
				.timestamp(dateTime)
				.temperature(dailyWeatherRecord.getMain().getTemp())
				.isMinTempReached(dailyWeatherRecord.getMain().getTemp_min() < location.getTemp().getMin())
                .isMaxTempReached(dailyWeatherRecord.getMain().getTemp_max() > location.getTemp().getMax())
                .build();
	 }

}
