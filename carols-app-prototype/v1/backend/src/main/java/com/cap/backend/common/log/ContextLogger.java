package com.cap.backend.common.log;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;

/*
 * A delegating logger that enriches each log line with context that can be built up throughout the life of a request.
 * The idea is that you have fewer log lines for each request/message/whatever scope but that you build context as you
 * go, then at the end you log the result and all of the context that led up to that result is logged with it as
 * `StructuredArguments`
 */
public class ContextLogger extends DelegatingLogger {

	private static String DEFAULT_LOGGER_CONTEXT_KEY = "ctx";
	private static ThreadLocal<LoggableContext> ctx = new ThreadLocal<>();

	private String loggerContextKey = ContextLogger.DEFAULT_LOGGER_CONTEXT_KEY;

	public ContextLogger(Logger logger) {
		super(logger);
	}

	public ContextLogger(Class<?> clazz) {
		this(LoggerFactory.getLogger(clazz));
	}

	@Override
	protected StructuredArgument getCtxAsStructuredArgument() {
		return StructuredArguments.raw(loggerContextKey, ContextLogger.getCtx().asJson());
	}

	public static void reset() {
		ContextLogger.getCtx().reset();
	}

	public void resetContext() {
		ContextLogger.reset();
	}

	public static void addContext(String key, Exception value) {
		ContextLogger.getCtx().add(key, value.toString());
		ContextLogger.getCtx().add(key + ".message", value.getMessage());
		if (value.getCause() != null) {
			ContextLogger.getCtx().add(key + ".cause", value.getCause().toString());
		}
		ContextLogger.getCtx().add(key + ".stack_trace", Arrays.toString(value.getStackTrace()));
	}

	public static void addContext(String key, Object value) {
		ContextLogger.getCtx().add(key, value != null ? value.toString() : "null");
	}

	public ContextLogger withContext(String key, Exception value) {
		ContextLogger.addContext(key, value);
		return this;
	}

	public ContextLogger withContext(String key, Object value) {
		ContextLogger.addContext(key, value);
		return this;
	}

	public static String object2Json(Object value) throws JsonProcessingException {
		return ContextLogger.getCtx().object2Json(value);
	}

	private static LoggableContext getCtx() {
		LoggableContext loggableContext = ContextLogger.ctx.get();
		if (loggableContext == null) {
			loggableContext = new LoggableContext();
			ContextLogger.ctx.set(loggableContext);
		}
		return loggableContext;
	}
}