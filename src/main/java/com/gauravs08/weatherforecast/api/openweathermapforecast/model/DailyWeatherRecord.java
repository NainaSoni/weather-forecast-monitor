package com.gauravs08.weatherforecast.api.openweathermapforecast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyWeatherRecord {
	private long dt;
	private String dt_txt;
	
	private MainTemp main;
}