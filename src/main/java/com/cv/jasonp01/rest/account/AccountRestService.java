package com.cv.jasonp01.rest.account;

import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cv.jasonp01.entity.Account;
import com.cv.jasonp01.entity.Account.AccountBuilder;
import com.cv.jasonp01.main.RunJasonP01;
import com.cv.jasonp01.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonPatch;

@Path("/v1/accounts/account")
public class AccountRestService {

	private static final Logger logger = Logger.getLogger(AccountRestService.class);

	@Autowired
	private AccountService accountService;
	
	@GET
	@Path("/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("accountId") Long accountId) {
		Account account = accountService.getAccount(accountId);
		if (account == null) {
			return Response.status(404).build();
		} else {
			return Response.status(200).entity(account).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts() {
		logger.info("getAccounts01");
		List<Account> accounts = accountService.getAccounts();
		Response response = Response.status(200).entity(accounts).build();
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account) {
		logger.info("createAccount01");
		accountService.createAccount(account);
		Response response = Response.status(201).entity(RunJasonP01.BASE_URI.toString() + "v1/accounts/account/" + account.getId()).build();
		return response;
	}

	@PATCH
	@Path("/{accountId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response patchAccount(@PathParam("accountId") Long accountId, JsonNode patch) {
		Account account = accountService.getAccount(accountId);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode accountNode = objectMapper.convertValue(account, JsonNode.class);

		JsonNode accountNewNode = JsonPatch.apply(patch, accountNode);
		try {
			Account accountNew = objectMapper.treeToValue(accountNewNode, Account.class);
			accountService.updateAccount(accountNew);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		
		Response response = Response.status(200).build();
		return response;
	}

}
