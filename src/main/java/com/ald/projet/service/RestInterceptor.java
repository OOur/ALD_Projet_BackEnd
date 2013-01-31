package com.ald.projet.service;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;
import org.slf4j.LoggerFactory;

@Provider
@ServerInterceptor
//@Precedence("HEADER_DECORATOR")
public class RestInterceptor implements MessageBodyWriterInterceptor {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RestInterceptor.class);	
	
	public void write(MessageBodyWriterContext context) throws IOException,
	WebApplicationException {
		LOG.info("on passe dans le write !");
		System.out.println("on passe dans le write !");
//		context.getHeaders().add("ACCESS_CONTROL_ALLOW_ORIGIN", "*");
		context.getHeaders().add("Access-Control-Allow-Origin", "*");
		context.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		context.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
		context.getHeaders().add("Access-Control-Max-Age", "1800");//30 min
		context.proceed();



	}

}
