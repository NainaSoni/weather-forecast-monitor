package com.gauravs08.weatherforecast.api.openweathermapforecast.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastOWM {
    private City city;
    private int cnt;
   
    private List<DailyWeatherRecord> list;
}

