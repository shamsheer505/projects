package com.hotelbooking.customer.aop;

import java.util.UUID;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ControllerAspect {

	private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

	@Before("execution(* com.hotelbooking.customer.controller.*.*(..))")
	public void beforeBookingAdvice() {
		// System.out.println("Before calling login method in login controller");
		final String correlationId = generateUniqueCorrelationId();
		MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
	}

	@AfterThrowing("execution(* com.hotelbooking.customer.controller.*.*(..))")
	public void bookingException() {
		MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
	}

	@After("execution(* com.hotelbooking.customer.controller.*.*(..))")
	public void afterBookingAdvice() {
		// System.out.println("Before calling login method in login controller");
		MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
	}

	private String generateUniqueCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
