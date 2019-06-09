package com.gauravs08.weatherforecast.api.openweathermapforecast.service;

import com.gauravs08.weatherforecast.api.openweathermapforecast.model.ForecastOWM;

public interface OWMService {

	public ForecastOWM getForecastForLocation(String location);

}
