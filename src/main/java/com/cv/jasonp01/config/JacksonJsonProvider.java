package com.cv.jasonp01.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonProvider extends JacksonJaxbJsonProvider {

	private static ObjectMapper objectMapperAtRest = new ObjectMapper();

	static {
//		objectMapperAtRest.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		objectMapperAtRest.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		objectMapperAtRest.configure(SerializationFeature.INDENT_OUTPUT, true); // Different from default so you can test it :)
//		objectMapperAtRest.setSerializationInclusion(JsonInclude.Include.ALWAYS);
	}

	public JacksonJsonProvider() {
		super();
		setMapper(objectMapperAtRest);
	}
}