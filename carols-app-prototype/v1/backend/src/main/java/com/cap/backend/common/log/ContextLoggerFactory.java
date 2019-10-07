package com.cap.backend.common.log;

import org.slf4j.Logger;

public class ContextLoggerFactory {
	public static ContextLogger getLogger(Class<?> clazz) {
		return new ContextLogger(clazz);
	}

	public static ContextLogger getLogger(Logger logger) {
		return new ContextLogger(logger);
	}
}
