package com.cv.jasonp01.main;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cv.jasonp01.config.JerseyConfig;
import com.cv.jasonp01.config.SpringConfig;

public class RunJasonP01 {

	private static final Logger logger = Logger.getLogger(RunJasonP01.class);
	public static final URI BASE_URI = URI.create("http://localhost:8080/jasonp01/");

	public static void main(String[] args) {
		try {
			Logger rootLogger = Logger.getRootLogger();
			rootLogger.setLevel(Level.INFO);
			rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %5p %t %c{1}:%M:%L - %m%n")));

			final JerseyConfig resourceConfig = new JerseyConfig();
			resourceConfig.property("contextConfig", new AnnotationConfigApplicationContext(SpringConfig.class));
			final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					server.shutdownNow();
				}
			}));
			server.start();
			System.out.println("started");
			logger.info(String.format("Check server at %s", BASE_URI));

			Thread.currentThread().join();
		} catch (IOException | InterruptedException ex) {
			logger.error("Exception", ex);
		}
	}

}
