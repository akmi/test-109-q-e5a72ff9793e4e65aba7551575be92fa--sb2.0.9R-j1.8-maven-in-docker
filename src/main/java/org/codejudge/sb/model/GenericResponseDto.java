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
public class GenericResponseDto {
	private String status;
	private String reason;
	
	public GenericResponseDto(String status) {
		this.status = status;
	}
}
