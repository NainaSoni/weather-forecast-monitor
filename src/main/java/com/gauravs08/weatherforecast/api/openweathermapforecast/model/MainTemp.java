package com.gauravs08.weatherforecast.api.openweathermapforecast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class MainTemp {
	  private double temp;
	  private double temp_min;
	  private double temp_max;
}
