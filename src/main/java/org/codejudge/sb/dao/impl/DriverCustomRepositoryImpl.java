package org.codejudge.sb.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codejudge.sb.dao.api.DriverCustomRepository;
import org.codejudge.sb.model.DriverDto;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DriverCustomRepositoryImpl implements DriverCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DriverDto> getAvailableCabs(Double latitude, Double longitude) throws JsonProcessingException {
		log.info("got request to get available cabs..");
		String queryStr = "SELECT name, car_number, phone_number  " + 
				" From (SELECT id, name, email, car_number, phone_number, license_number, latitude, longitude, ( 6371 * acos( cos( radians(?) )  " + 
				"          * cos( radians( latitude ) ) " + 
				"          * cos( radians( longitude ) - radians(?) ) + " + 
				"             sin( radians(?) ) " + 
				"          * sin(radians(latitude)) ) ) distance " + 
				"      From driver) z " + 
				" Where distance <= 4 " + 
				" ORDER BY distance ";
        try {
            javax.persistence.Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter(1, latitude);
            query.setParameter(2, longitude);
            query.setParameter(3, latitude);

            List<Object[]> resultList = query.getResultList();
            List<DriverDto> response = new ArrayList<>();
            if (resultList != null && resultList.size() > 0) {
            	for (Object[] result : resultList) {
            		BigInteger object = (BigInteger) result[2];
					DriverDto dto = new DriverDto((String)result[0], (String) result[1], object.longValue());
					response.add(dto);
				}
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
	}

}
