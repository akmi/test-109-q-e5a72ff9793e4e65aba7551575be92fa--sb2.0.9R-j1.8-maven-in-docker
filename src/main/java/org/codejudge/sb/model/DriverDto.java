package org.codejudge.sb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true, doNotUseGetters=true)
@AllArgsConstructor
public class DriverDto {
	private String name;
	private String car_number;
	private Long phone_number;
}
