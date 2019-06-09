package com.gauravs08.weatherforecast.api.openweathermapforecast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gauravs08.weatherforecast.api.openweathermapforecast.model.ForecastOWM;
import com.gauravs08.weatherforecast.exception.ForecastApiException;
import com.gauravs08.weatherforecast.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OWMServiceImpl implements OWMService {
	@Autowired
	private final RestTemplate restTemplate;

	private final String applicationId;

	public OWMServiceImpl(@Autowired RestTemplate restTemplate, @Value("${service.OWM.api.key}") String appKey) {
		this.restTemplate = restTemplate;
		this.applicationId = appKey;
	}

	/**OpenWeatherMap Api call for location and convert the response into ForecastOWM.
	 *@param String : Location
	 *@apiParam Location
	 *@apiParam Units Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
	 *@apiParam application id :NOTE: Make sure to register to openWeatherMapapi to get your own api Key
	 *@return ForecastOWM  
	 */
	@Override
	public ForecastOWM getForecastForLocation(String location) {

		ForecastOWM forecastOWM = new ForecastOWM();
		String url = UriComponentsBuilder.fromUriString(
				"http://api.openweathermap.org/data/2.5/forecast?q=" + location + "&units=metric&appid=" + applicationId)
				.build().toUriString();
		try {
			forecastOWM = restTemplate.getForObject(url, ForecastOWM.class);
		} catch (ForecastApiException e) {
			LOGGER.error("OpenWeather URI executing failure ", url, e.getMessage());
			throw new ForecastApiException("OpenWeather URI executing failure");
		} catch (HttpClientErrorException e) {
			LOGGER.error("OpenWeather URI executing failure ", url, e.getMessage());
			throw new ResourceNotFoundException("URI");
		} catch(Exception e) {
			LOGGER.error("OpenWeathermap connection api failure ", url, e.getMessage());
			throw new ResourceNotFoundException("OpenWeathermap api");
		}
		return forecastOWM;
	}
}
