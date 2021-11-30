package com.newton.aaw.hr.api;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

	private String timestamp;
	private int status;
	private String error;
	private String path;
}
