package com.cv.jasonp01.service;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cv.jasonp01.entity.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountService {

	private static final Logger logger = Logger.getLogger(AccountService.class);

	private static final String FILE_DATA = "data.json";

	public List<Account> getAccounts() {
		try {
			byte[] jsonData = Files.readAllBytes(Paths.get(FILE_DATA));
			ObjectMapper objectMapper = new ObjectMapper();
			List<Account> accounts = objectMapper.readValue(jsonData, new TypeReference<List<Account>>() {
			});
			logger.info("getAccounts [accounts:" + (accounts == null ? "ISNULL" : accounts.size()) + "]");
			return accounts;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<Account>();
	}

	public Account getAccount(Long accountId) {
		if (accountId == null) {
			return null;
		}
		List<Account> accounts = getAccounts();
		Optional<Account> accountOpt = accounts.stream().filter(x -> accountId.equals(x.getId())).findAny();
		if (accountOpt.isPresent()) {
			return accountOpt.get();
		}
		return null;
	}

	public void createAccount(Account account) {
		if (account == null) {
			return;
		}
		try {
			StringWriter stringAcc = new StringWriter();
			ObjectMapper objectMapper = new ObjectMapper();

			List<Account> accounts = getAccounts();

			accounts.add(account);
			objectMapper.writeValue(stringAcc, accounts);
			Files.write(Paths.get(FILE_DATA), stringAcc.getBuffer().toString().getBytes());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateAccount(Account account) {
		if (account == null || account.getId() == null) {
			return;
		}
		try {
			List<Account> accounts = getAccounts();
			for (int i = 0; i < accounts.size(); i++) {
				Account accountCur = accounts.get(i);
				if (account.getId().equals(accountCur.getId())) {
					accounts.set(i, account);

					StringWriter stringAcc = new StringWriter();
					ObjectMapper objectMapper = new ObjectMapper();

					objectMapper.writeValue(stringAcc, accounts);
					Files.write(Paths.get(FILE_DATA), stringAcc.getBuffer().toString().getBytes());
					break;
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteAccount(Account account) {
		if (account == null || account.getId() == null) {
			return;
		}
		try {
			List<Account> accounts = getAccounts();
			for (int i = 0; i < accounts.size(); i++) {
				Account accountCur = accounts.get(i);
				if (account.getId().equals(accountCur.getId())) {
					accounts.remove(i);

					StringWriter stringAcc = new StringWriter();
					ObjectMapper objectMapper = new ObjectMapper();

					objectMapper.writeValue(stringAcc, accounts);
					Files.write(Paths.get(FILE_DATA), stringAcc.getBuffer().toString().getBytes());
					break;
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
