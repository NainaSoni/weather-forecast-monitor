package com.gauravs08.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class WeatherForecastMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastMonitorApplication.class, args);
	}

	 @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	 
}
