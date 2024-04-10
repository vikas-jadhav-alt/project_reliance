package com.vril.accountmanagement.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vril.accountmanagement.AccountStatus;
import com.vril.accountmanagement.model.Account;
import com.vril.accountmanagement.repository.AccountRepository;
import com.vril.accountmanagement.request.AccReqBody;
import com.vril.accountmanagement.request.AccUpdateReq;
import com.vril.accountmanagement.response.AccountResp;
import com.vril.accountmanagement.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional
	public Long createNewAccount(AccReqBody requestBody) {

		try {
			Account acc = new Account();
			acc.setAccountHolderName(requestBody.getAccHolderName());
			acc.setStatus(AccountStatus.ACTIVE.toString());

			return accountRepository.save(acc).getId();

		} catch (Exception e) {
			log.error("Error while creating new account: {}", e.getMessage());
		}

		return null;

	}

	@Override
	public AccountResp getAccDetails(Long accountID) {

		Optional<Account> out = accountRepository.findById(accountID);

		if (out.isPresent()) {
			Account account = out.get();

			AccountResp x = new AccountResp();
			x.setId(account.getId());
			x.setOwnerName(account.getAccountHolderName());
			x.setTotalAmount(account.getAccountBalance());

			return x;

		} else {
			return null;
		}

	}

	@Override
	public boolean updateAccountDetails(AccUpdateReq updateReq, Long accountID) {

		Optional<Account> account = accountRepository.findById(accountID);

		if (account.isPresent()) {
			if (updateReq.getMode().equals(AccountStatus.ModeOfTransaction.DEPOSIT))
				account.get().deposit(updateReq.getAmount());
			else
				account.get().withdraw(updateReq.getAmount());

			accountRepository.save(account.get());

			return true;
		}

		return false;

	}

}