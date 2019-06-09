package com.gauravs08.weatherforecast.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravs08.weatherforecast.api.rest.controller.ForecastController;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ForecastControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mvc;

	@Autowired
	ForecastController forecastController;

	@Test
	public void restApiControllerTest() throws Exception {

		//TimeUnit.SECONDS.sleep(5000); // until service is up

		String uri = "/forecast/report";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		ReportMaster executedResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				ReportMaster.class);

		assertNotNull(executedResult);

	}

}
