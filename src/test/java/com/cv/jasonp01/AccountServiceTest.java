package com.cv.jasonp01;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.cv.jasonp01.entity.Account;
import com.cv.jasonp01.entity.Account.AccountBuilder;
import com.cv.jasonp01.service.AccountService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {

	private static final Logger logger = Logger.getLogger(AccountServiceTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %5p %t %c{1}:%M:%L - %m%n")));
		logger.info("AccountTest.setUpBeforeClass()");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info("AccountTest.setUpBeforeClass()");
	}

	@Before
	public void setUp() throws Exception {
		logger.info("AccountTest.setUpBeforeClass()");
	}

	@After
	public void tearDown() throws Exception {
		logger.info("AccountTest.setUpBeforeClass()");
	}

	@Test
	public void test01Create() {
		AccountService accountService = new AccountService();
		List<Account> accounts = accountService.getAccounts();
		Integer initialSize = accounts.size();
		Account account = new AccountBuilder().setId((long) (Math.random()*Long.MAX_VALUE)).setName("John").setName("USD").setAmount(new BigDecimal(123.12)).build();
		accountService.createAccount(account);
		accounts = accountService.getAccounts();
		assertTrue(initialSize == accounts.size() - 1);
		accountService.deleteAccount(account);
	}

	@Test
	public void test02Get() {
		AccountService accountService = new AccountService();
		List<Account> accounts = accountService.getAccounts();
		Integer initialSize = accounts.size();
		Account account = new AccountBuilder().setId((long) (Math.random()*Long.MAX_VALUE)).setName("John").setName("USD").setAmount(new BigDecimal(123.12)).build();
		accountService.createAccount(account);
		Account accountNew = accountService.getAccount(account.getId());
		assertTrue(account.getId().equals(accountNew.getId()));
		accountService.deleteAccount(account);
	}

}
