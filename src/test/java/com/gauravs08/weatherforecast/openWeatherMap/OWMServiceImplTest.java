package com.gauravs08.weatherforecast.openWeatherMap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.gauravs08.weatherforecast.api.openweathermapforecast.model.ForecastOWM;
import com.gauravs08.weatherforecast.api.openweathermapforecast.service.OWMServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OWMServiceImplTest {

	@Value("${service.OWM.api.key}")
	private String appKey;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		OWMServiceImpl client = new OWMServiceImpl(restTemplate, appKey);

		ForecastOWM res = client.getForecastForLocation("McMurdo Station");

		ForecastOWM apiRes = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/forecast?q=McMurdo Station&units=metric&appid=" + appKey,
				ForecastOWM.class);

		assertThat(res).isEqualTo(apiRes);
	}
}
