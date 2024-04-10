package com.vril.accountmanagement.api;

import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vril.accountmanagement.AccountStatus;
import com.vril.accountmanagement.model.Account;
import com.vril.accountmanagement.request.AccReqBody;
import com.vril.accountmanagement.request.AccUpdateReq;
import com.vril.accountmanagement.response.AccountResp;
import com.vril.accountmanagement.response.ApiResponse;
import com.vril.accountmanagement.service.AccountService;

import lombok.NonNull;
import lombok.experimental.NonFinal;
import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping(value = "/api/v1/account")
@Transactional
@Validated
public class AccountController {

	@Autowired
	private AccountService accountService;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/service-status")
	ResponseEntity<String> isServiceUp() {
		return ResponseEntity.ok("Reliance Account Management Servive is up and running");
	}

	@PostMapping("/create")
	ResponseEntity<ApiResponse> createANewAccount(@Valid @RequestBody AccReqBody requestBody) {

		log.info("creating new account...");

		ApiResponse out = new ApiResponse();

		Long newAccountId = accountService.createNewAccount(requestBody);

		if (Objects.nonNull(newAccountId)) {
			out.getResponse().put("accountId", newAccountId);
			return ResponseEntity.ok(out);
		} else {
			out.setHasError(true);
			out.setErrorMessage("Issue while creating the new account");

			return ResponseEntity.internalServerError().body(out);
		}

	}

	@GetMapping("/{id}")
	ResponseEntity<ApiResponse> getAccountDetails(@PathVariable("id") @NonNull Long accountID) {

		log.info("...getting account details");

		ApiResponse response = new ApiResponse();

		AccountResp data = accountService.getAccDetails(accountID);

		response.getResponse().put("details", data);

		if (data != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/update/{id}")
	ResponseEntity<ApiResponse> updateAcountBalance(@PathVariable("id") @NonNull Long accountID,
			@Valid @RequestBody AccUpdateReq updateReq) {

		ApiResponse response = new ApiResponse();

		try {
			accountService.updateAccountDetails(updateReq, accountID);
		} catch (IllegalArgumentException i) {
			response.getResponse().put("updateStatus", "FAILED");
			response.setHasError(true);
			response.setErrorMessage("Transaction failed: " + i.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}

		response.getResponse().put("updateStatus", "SUCCESS");
		return ResponseEntity.ok(response);

	}

}
