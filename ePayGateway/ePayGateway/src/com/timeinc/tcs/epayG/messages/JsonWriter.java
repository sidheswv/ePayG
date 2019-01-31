package com.timeinc.tcs.epayG.messages;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.map.ObjectMapper;

public abstract class JsonWriter implements MessageBodyWriter<Object> {
	
	public long getSize(Object object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	
	public void writeTo(Object object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		// Explicitly use the Jackson ObjectMapper to write dates in ISO8601
		// format
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(k
		// SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false );
		mapper.writeValue(entityStream, object);
		// entityStream.write(((String) object).getBytes());
	}

}
