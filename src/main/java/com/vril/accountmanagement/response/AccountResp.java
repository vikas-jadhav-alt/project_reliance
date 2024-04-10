package com.vril.accountmanagement.response;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AccountResp implements Serializable {

	private Long id;

//	@JsonProperty("name")
	private String ownerName;

//	@JsonProperty("balance")
	private double totalAmount;

}
