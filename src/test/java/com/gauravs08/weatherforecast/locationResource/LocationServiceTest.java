package com.gauravs08.weatherforecast.locationResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravs08.weatherforecast.locationManager.model.Location;
import com.gauravs08.weatherforecast.locationManager.service.LocationService;
import com.gauravs08.weatherforecast.locationManager.service.LocationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationServiceTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	@Value("${service.input-location-file}")
	private String locationFilePath;

	Resource resource;
	LocationService location;

	/**To test Input location details file
	 * @throws Exception
	 */
	@Test
	public void FileRepositoryLoadTest() throws Exception {

		Set<Location> actuallocDetails = location.getAllLocationDetails();

		Set<Location> expectedLocDetails = Arrays
				.stream(objectMapper.readValue(resource.getInputStream(), Location[].class))
				.collect(Collectors.toSet());

		assertThat(expectedLocDetails).isEqualTo(actuallocDetails);
	}

	@Before
	public void getRepositoryLoadTest() {
		location = new LocationServiceImpl(objectMapper, resourceLoader, locationFilePath);
		resource = resourceLoader.getResource(locationFilePath);

		assertNotNull(resource);
	}
}
