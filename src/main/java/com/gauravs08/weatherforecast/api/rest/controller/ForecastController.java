package com.gauravs08.weatherforecast.api.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gauravs08.weatherforecast.exception.ResourceNotFoundException;
import com.gauravs08.weatherforecast.reportManager.model.ReportMaster;
import com.gauravs08.weatherforecast.reportManager.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/forecast")
@RequiredArgsConstructor
public class ForecastController {
	
	@Autowired
	private ReportService reportService;
	
	/**Api to get weather forecast report
	 * @return ReportMaster
	 */
	@GetMapping("/report")
	public ReportMaster getReport() {
        return Optional.ofNullable(reportService.getReport())
        		.orElseThrow(() -> new ResourceNotFoundException("Report"));
    }
}
