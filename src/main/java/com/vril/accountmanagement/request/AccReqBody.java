package com.vril.accountmanagement.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AccReqBody {

	@NotEmpty(message = "Account Holer Name Required")
	private String accHolderName;

}
