package org.codejudge.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codejudge.sb.exception.RangeException;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true, doNotUseGetters=true)
@Entity
@Table(name="driver")
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private Long phoneNumber;
	
	@Column(name="license_number")
	private String licenseNumber;
	
	@Column(name="car_number")
	private String carNumber;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;
	
	
	public static void validateForUpsertion(Driver driver) throws RangeException {
		if (driver == null) {
			throw new IllegalArgumentException("Driver can't be empty!");
		}
		driver.validateForCreation();
	}
	
	public static void validateForLocationInput(Driver driver) {
		if (driver == null) {
			throw new IllegalArgumentException("Location can't be empty!");
		}
		driver.validateForBookingCreation();
	}
	
	public void transformForSendLocation(Driver driver) {
		this.latitude = driver.getLatitude();
		this.longitude = driver.getLongitude();
	}

	private void validateForCreation() throws RangeException {
		this.validateName();
		this.validateEmail();
		this.validatePhoneNumber();
		this.validateLicenseNumber();
		this.validateCarNumber();
	}
	
	private void validateForBookingCreation() {
		this.validateLatitude();
		this.validateLongitude();
	}

	private void validateName() {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Name can't be empty!");
		}
	}

	private void validateEmail() {
		if (StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException("Email can't be empty!");
		}
	}

	private void validatePhoneNumber() {
		if (null == phoneNumber) {
			throw new IllegalArgumentException("Phone Number can't be empty!");
		}
		if (phoneNumber == 0) {
			throw new IllegalArgumentException("Phone Number can't be 0!");
		}
		int length = (int) (Math.log10(phoneNumber) + 1);
		if (length != 10) {
			throw new IllegalArgumentException("Phone Number must be 10 digits!");
		}
	}
	
	private void validateLatitude() {
		if (null == latitude) {
			throw new IllegalArgumentException("Latitude can't be empty!");
		}
	}
	
	private void validateLongitude() {
		if (null == longitude) {
			throw new IllegalArgumentException("Longitude can't be empty!");
		}
	}
	
	private void validateLicenseNumber() {
		if (StringUtils.isEmpty(licenseNumber)) {
			throw new IllegalArgumentException("License Number can't be empty!");
		}
	}

	private void validateCarNumber() {
		if (StringUtils.isEmpty(carNumber)) {
			throw new IllegalArgumentException("Car Number can't be empty!");
		}
	}
}
