package com.vril.accountmanagement.service;

import com.vril.accountmanagement.request.AccReqBody;
import com.vril.accountmanagement.request.AccUpdateReq;
import com.vril.accountmanagement.response.AccountResp;

public interface AccountService {

	Long createNewAccount(AccReqBody requestBody);

	AccountResp getAccDetails(Long accountID);

	boolean updateAccountDetails(AccUpdateReq updateReq, Long accountID);

}
