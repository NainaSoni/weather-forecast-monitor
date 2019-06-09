package com.gauravs08.weatherforecast.exception;

import org.springframework.web.client.RestClientException;

/** Forecast api Exception class extends RestClientException
 * 
 * @author sharm
 *
 */
public class ForecastApiException extends RestClientException{

	public ForecastApiException(String msg) {
		super(msg);
	}

}
