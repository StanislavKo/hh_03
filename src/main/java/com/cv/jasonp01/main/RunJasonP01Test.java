package com.cv.jasonp01.main;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cv.jasonp01.config.JerseyConfig;
import com.cv.jasonp01.config.SpringConfig;

public class RunJasonP01Test {

	private static final Logger logger = Logger.getLogger(RunJasonP01Test.class);

	public static void main(String[] args) {
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %5p %t %c{1}:%M:%L - %m%n")));

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		String responseList = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		String responseGet = target.path("/1").request().accept(MediaType.APPLICATION_JSON).get(String.class);
		logger.info(responseList);
		logger.info(responseGet);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/jasonp01/v1/accounts/account").build();
	}

}
