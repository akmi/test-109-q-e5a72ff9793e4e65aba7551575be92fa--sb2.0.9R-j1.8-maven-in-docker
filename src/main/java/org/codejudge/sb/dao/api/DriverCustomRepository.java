package org.codejudge.sb.dao.api;

import java.util.List;

import org.codejudge.sb.model.DriverDto;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface DriverCustomRepository {
	public List<DriverDto> getAvailableCabs(Double latitude, Double longitude) throws JsonProcessingException;
}
