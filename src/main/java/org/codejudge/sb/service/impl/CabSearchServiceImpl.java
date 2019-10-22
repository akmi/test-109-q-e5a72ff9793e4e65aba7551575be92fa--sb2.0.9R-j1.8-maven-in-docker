package org.codejudge.sb.service.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.codejudge.sb.dao.api.DriverCustomRepository;
import org.codejudge.sb.dao.api.DriverRepository;
import org.codejudge.sb.entity.Driver;
import org.codejudge.sb.exception.RangeException;
import org.codejudge.sb.model.DriverDto;
import org.codejudge.sb.model.GenericResponseDto;
import org.codejudge.sb.service.api.CabSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
@Slf4j
public class CabSearchServiceImpl implements CabSearchService {
	
	@Autowired
	private DriverRepository repo;
	
	@Autowired
	private DriverCustomRepository repo2;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Driver create(Driver driver) throws RangeException {
		log.info("input: " + driver);
		Driver.validateForUpsertion(driver);
		Driver savedDriver = repo.getByEmailOrPhoneOrLicenseOrCar(driver.getEmail(), driver.getPhoneNumber(), driver.getLicenseNumber(), driver.getCarNumber());
		if (savedDriver == null) {
			savedDriver = repo.save(driver);
			log.info("saved driver successfully: " + savedDriver);
			return savedDriver;
		}
		throw new IllegalArgumentException("A driver is already registered with same email/phone number/license/car number!");
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public GenericResponseDto sendLocation(Integer id, Driver driver) throws RangeException {
		log.info("input id: " + id + ", input location: " + driver);
		Driver.validateForLocationInput(driver);
		validateId(id);
		driver.setId(id);
		Driver savedDriver = get(id);
		savedDriver.transformForSendLocation(driver);
		repo.save(savedDriver);
		return new GenericResponseDto("success");
	}

	@Override
	public Driver get(Integer id) {
		log.info("input id: " + id );
		validateId(id);
		Driver product = repo.getById(id);
		if (product == null) {
			throw new IllegalArgumentException("No driver found!");
		}
		return product;
	}
	
	@Override
	public List<DriverDto> getAvailableCabs(Driver driver) throws FileNotFoundException, JsonProcessingException {
		log.info("input driver for getting available cars: " + driver);
		Driver.validateForLocationInput(driver);
		Iterable<Driver> findAll = repo.findAll();
//		List<Object[]> response = repo.getAvailableCabsV2(driver.getLatitude(), driver.getLongitude());
		List<DriverDto> response = repo2.getAvailableCabs(driver.getLatitude(), driver.getLongitude());
//		List<DriverDto> dtos = new ArrayList<>();
//		for (DriverProjection projection : response) {
//			dtos.add(new DriverDto(projection.getName, projection.car_number(), projection.phone_number()));
//		}
		return response;
		
	}
	
	private void validateId(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Product id can't be null!");
		}
	}
}
