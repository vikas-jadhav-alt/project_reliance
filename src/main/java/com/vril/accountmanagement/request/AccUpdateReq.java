package com.vril.accountmanagement.request;

import com.vril.accountmanagement.AccountStatus;

import lombok.Data;
import lombok.NonNull;

@Data
public class AccUpdateReq {


	@NonNull
	private AccountStatus.ModeOfTransaction mode;

	@NonNull
	private Double amount;
}
