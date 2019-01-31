package com.timeinc.tcs.epayG.messages;

import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;

@Provider
public class ExpressCheckoutJson extends JsonWriter {



	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		boolean isWritable = false;
		if (List.class.isAssignableFrom(type)
				&& genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
			isWritable = (actualTypeArgs.length == 1 && actualTypeArgs[0]
					.equals(PayPalExpressResponse.class));
		} else if (type == PayPalExpressResponse.class) {
			isWritable = true;
		}
		return isWritable;
	}


}
