package com.cv.jasonp01;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cv.jasonp01.config.JerseyConfig;
import com.cv.jasonp01.config.SpringConfig;
import com.cv.jasonp01.entity.Account;
import com.cv.jasonp01.entity.Account.AccountBuilder;
import com.cv.jasonp01.service.AccountService;

public class AccountRestTest extends JerseyTest {

	private static final Logger logger = Logger.getLogger(AccountRestTest.class);

	@Override
	protected Application configure() {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		return new JerseyConfig().property("contextConfig", context);
	}

	@Test
	public void testList() throws Exception {
		final Response responseList = target("v1/accounts/account").request().get(Response.class);
		Assert.assertTrue(responseList.getStatus() == 200);
	}

	@Test
	public void testGet() throws Exception {
		final Response responseGet = target("v1/accounts/account/1").request().get(Response.class);
		Assert.assertTrue(responseGet.getStatus() == 200 || responseGet.getStatus() == 404);
	}

}
