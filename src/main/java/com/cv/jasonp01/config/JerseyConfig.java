package com.cv.jasonp01.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RequestContextFilter.class);
		register(JacksonJsonProvider.class);
		packages("com.cv.jasonp01.rest");
	}

}
