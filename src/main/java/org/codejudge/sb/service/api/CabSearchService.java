package org.codejudge.sb.service.api;

import java.io.FileNotFoundException;
import java.util.List;

import org.codejudge.sb.entity.Driver;
import org.codejudge.sb.exception.RangeException;
import org.codejudge.sb.model.DriverDto;
import org.codejudge.sb.model.GenericResponseDto;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CabSearchService {
	public Driver create(Driver product) throws RangeException;
	public GenericResponseDto sendLocation(Integer id, Driver driver) throws RangeException;
	public Driver get(Integer id) throws FileNotFoundException;
	public List<DriverDto> getAvailableCabs(Driver driver) throws FileNotFoundException, JsonProcessingException;
}
