package org.codejudge.sb.dao.api;

import java.util.List;

import org.codejudge.sb.entity.Driver;
import org.codejudge.sb.model.DriverDto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DriverRepository extends PagingAndSortingRepository<Driver, Integer>, JpaSpecificationExecutor<Driver> {
	public Driver getById(Integer id);
	
	@Query(value="SELECT name, car_number, phone_number " + 
			" From (SELECT name, car_number, phone_number, ( 6371 * acos( cos( radians(:latitude) )  " + 
			"          * cos( radians( latitude ) ) " + 
			"          * cos( radians( longitude ) - radians(:longitude) ) + " + 
			"             sin( radians(:latitude) ) " + 
			"          * sin(radians(latitude)) ) ) distance " + 
			"      From Driver) z " + 
			" Where distance <= 4 " + 
			" ORDER BY distance ", nativeQuery=true)
	public List<DriverDto> getAvailableCabs(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
	
	@Query(value="SELECT name, car_number, phone_number  " + 
			" From (SELECT id, name, email, car_number, phone_number, license_number, latitude, longitude, ( 6371 * acos( cos( radians(:latitude) )  " + 
			"          * cos( radians( latitude ) ) " + 
			"          * cos( radians( longitude ) - radians(:longitude) ) + " + 
			"             sin( radians(:latitude) ) " + 
			"          * sin(radians(latitude)) ) ) distance " + 
			"      From Driver) z " + 
			" Where distance <= 4 " + 
			" ORDER BY distance ", nativeQuery=true)
	public List<Object[]> getAvailableCabsV2(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
	
	@Query(" SELECT d from Driver d where d.email = :email or d.phoneNumber = :phoneNumber or d.licenseNumber = :licenseNumber or d.carNumber = :carNumber ")
	public Driver getByEmailOrPhoneOrLicenseOrCar(@Param("email") String email, @Param("phoneNumber") Long phoneNumber, 
			@Param("licenseNumber") String licenseNumber, @Param("carNumber") String carNumber);
}
