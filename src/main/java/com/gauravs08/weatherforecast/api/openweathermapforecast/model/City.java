package com.gauravs08.weatherforecast.api.openweathermapforecast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City{
	private long id;
	private String name;
	private String country;
}