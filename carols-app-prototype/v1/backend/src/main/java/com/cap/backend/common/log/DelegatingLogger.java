package com.cap.backend.common.log;

import org.slf4j.Logger;
import org.slf4j.Marker;

import net.logstash.logback.argument.StructuredArgument;

/*
 * This class exists to implement, via delegation, the Logger interface.  It is abstract and package-private because it
 * should never be instantiated.  Instantiate instead a `ContextLogger` and enjoy log lines enriched with context as you
 * add it.
 */
abstract class DelegatingLogger implements Logger {
	private final Logger delegateLogger;

	DelegatingLogger(Logger logger) {
		delegateLogger = logger;
	}

	protected abstract StructuredArgument getCtxAsStructuredArgument();

	@Override
	public String getName() {
		return delegateLogger.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return delegateLogger.isTraceEnabled();
	}

	@Override
	public void trace(String msg) {
		if (isTraceEnabled()) {
			delegateLogger.trace(msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(String format, Object arg) {
		if (isTraceEnabled()) {
			delegateLogger.trace(format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		if (isTraceEnabled()) {
			delegateLogger.trace(format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(String format, Object... arguments) {
		if (isTraceEnabled()) {
			delegateLogger.trace(format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(String msg, Throwable t) {
		if (isTraceEnabled()) {
			delegateLogger.trace(msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return delegateLogger.isTraceEnabled(marker);
	}

	@Override
	public void trace(Marker marker, String msg) {
		if (isTraceEnabled(marker)) {
			delegateLogger.trace(marker, msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		if (isTraceEnabled(marker)) {
			delegateLogger.trace(marker, format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		if (isTraceEnabled(marker)) {
			delegateLogger.trace(marker, format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		if (isTraceEnabled(marker)) {
			delegateLogger.trace(marker, format, argArray, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		if (isTraceEnabled(marker)) {
			delegateLogger.trace(marker, msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isDebugEnabled() {
		return delegateLogger.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		if (isDebugEnabled()) {
			delegateLogger.debug(msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(String format, Object arg) {
		if (isDebugEnabled()) {
			delegateLogger.debug(format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		if (isDebugEnabled()) {
			delegateLogger.debug(format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(String format, Object... arguments) {
		if (isDebugEnabled()) {
			delegateLogger.debug(format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(String msg, Throwable t) {
		if (isDebugEnabled()) {
			delegateLogger.debug(msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return delegateLogger.isDebugEnabled(marker);
	}

	@Override
	public void debug(Marker marker, String msg) {
		if (isDebugEnabled(marker)) {
			delegateLogger.debug(marker, msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		if (isDebugEnabled(marker)) {
			delegateLogger.debug(marker, format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		if (isDebugEnabled(marker)) {
			delegateLogger.debug(marker, format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		if (isDebugEnabled(marker)) {
			delegateLogger.debug(marker, format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		if (isDebugEnabled(marker)) {
			delegateLogger.debug(marker, msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isInfoEnabled() {
		return delegateLogger.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		if (isInfoEnabled()) {
			delegateLogger.info(msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(String format, Object arg) {
		if (isInfoEnabled()) {
			delegateLogger.info(format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		if (isInfoEnabled()) {
			delegateLogger.info(format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(String format, Object... arguments) {
		if (isInfoEnabled()) {
			delegateLogger.info(format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(String msg, Throwable t) {
		if (isInfoEnabled()) {
			delegateLogger.info(msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return delegateLogger.isInfoEnabled(marker);
	}

	@Override
	public void info(Marker marker, String msg) {
		if (isInfoEnabled(marker)) {
			delegateLogger.info(marker, msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		if (isInfoEnabled(marker)) {
			delegateLogger.info(marker, format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		if (isInfoEnabled(marker)) {
			delegateLogger.info(marker, format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		if (isInfoEnabled(marker)) {
			delegateLogger.info(marker, format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		if (isInfoEnabled(marker)) {
			delegateLogger.info(marker, msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isWarnEnabled() {
		return delegateLogger.isWarnEnabled();
	}

	@Override
	public void warn(String msg) {
		if (isWarnEnabled()) {
			delegateLogger.warn(msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(String format, Object arg) {
		if (isWarnEnabled()) {
			delegateLogger.warn(format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(String format, Object... arguments) {
		if (isWarnEnabled()) {
			delegateLogger.warn(format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		if (isWarnEnabled()) {
			delegateLogger.warn(format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(String msg, Throwable t) {
		if (isWarnEnabled()) {
			delegateLogger.warn(msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return delegateLogger.isWarnEnabled(marker);
	}

	@Override
	public void warn(Marker marker, String msg) {
		if (isWarnEnabled(marker)) {
			delegateLogger.warn(marker, msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		if (isWarnEnabled(marker)) {
			delegateLogger.warn(marker, format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		if (isWarnEnabled(marker)) {
			delegateLogger.warn(marker, format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		if (isWarnEnabled(marker)) {
			delegateLogger.warn(marker, format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		if (isWarnEnabled(marker)) {
			delegateLogger.warn(marker, msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isErrorEnabled() {
		return delegateLogger.isErrorEnabled();
	}

	@Override
	public void error(String msg) {
		if (isErrorEnabled()) {
			delegateLogger.error(msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(String format, Object arg) {
		if (isErrorEnabled()) {
			delegateLogger.error(format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		if (isErrorEnabled()) {
			delegateLogger.error(format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(String format, Object... arguments) {
		if (isErrorEnabled()) {
			delegateLogger.error(format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(String msg, Throwable t) {
		if (isErrorEnabled()) {
			delegateLogger.error(msg, t, getCtxAsStructuredArgument());
		}
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return delegateLogger.isErrorEnabled(marker);
	}

	@Override
	public void error(Marker marker, String msg) {
		if (isErrorEnabled(marker)) {
			delegateLogger.error(marker, msg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		if (isErrorEnabled(marker)) {
			delegateLogger.error(marker, format, arg, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		if (isErrorEnabled(marker)) {
			delegateLogger.error(marker, format, arg1, arg2, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		if (isErrorEnabled(marker)) {
			delegateLogger.error(marker, format, arguments, getCtxAsStructuredArgument());
		}
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		if (isErrorEnabled(marker)) {
			delegateLogger.error(marker, msg, t, getCtxAsStructuredArgument());
		}
	}
}
