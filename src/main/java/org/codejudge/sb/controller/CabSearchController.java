package org.codejudge.sb.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codejudge.sb.entity.Driver;
import org.codejudge.sb.model.DriverDto;
import org.codejudge.sb.model.GenericResponseDto;
import org.codejudge.sb.service.api.CabSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabSearchController {

	@Autowired
	CabSearchService service;
	
	@GetMapping("/")
	@ResponseBody 
	public String test() throws IOException {
		return "Hello Docker world!";
	}

	@PostMapping("/api/v1/driver/register/")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Driver createDriver(@RequestBody Driver product) throws Exception{
		return service.create(product);
	}
	
	@PostMapping("/api/v1/driver/{id}/sendLocation/")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Object update(@PathVariable(value = "id") Integer id, @RequestBody Driver driver) throws Exception {
		GenericResponseDto resp = service.sendLocation(id, driver);
		return resp;
	}
	
	@PostMapping("/api/v1/passenger/available_cabs/")
	@ResponseBody
	public Object getAvailableCabs(@RequestBody Driver driver) throws Exception {
		List<DriverDto> response = service.getAvailableCabs(driver);
		int size = response.size();
		if (size == 0) {
			Map<String, String> response1 = new HashMap<>();
			response1.put("message", "No cabs available!");
			return response1;
		}
		Map<String, List<DriverDto>> finalResponse = new HashMap<>();
		finalResponse.put("available_cabs", response);
		return finalResponse;
	}
}
