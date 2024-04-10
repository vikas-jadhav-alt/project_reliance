package com.vril.accountmanagement.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
//import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiResponse {

	private Map<String, Object> response = null;

	private String errorMessage = null;

	private Boolean hasError = null;

	public ApiResponse() {
		this.response = new HashMap<>();
	}

}
