package com.cap.backend.common.log;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.Marker;

import com.google.common.collect.ImmutableList;

import net.logstash.logback.argument.StructuredArgument;

@ExtendWith(MockitoExtension.class)
class DelegatingLoggerTest {

	private class TestDelegatingLogger extends DelegatingLogger {
		TestDelegatingLogger(Logger logger) {
			super(logger);
		}

		@Override
		protected StructuredArgument getCtxAsStructuredArgument() {
			return mockCtx;
		}
	}

	@Mock
	private Logger mockLogger;
	@Mock
	private Marker marker;
	@Mock
	private StructuredArgument mockCtx;

	private TestDelegatingLogger log;

	@BeforeEach
	void setUp() {
		log = new TestDelegatingLogger(mockLogger);
	}

	@AfterEach
	void tearDown() {
		Mockito.verifyNoMoreInteractions(mockLogger);
	}

	@Test
	void getName() {
		log.getName();
		Mockito.verify(mockLogger).getName();
	}

	@Test
	void isTraceEnabled0() {
		Mockito.when(mockLogger.isTraceEnabled()).thenReturn(true);
		Assertions.assertTrue(log.isTraceEnabled());
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace0(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled()).thenReturn(levelIsEnabled);
		log.trace("words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace("words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace1(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled()).thenReturn(levelIsEnabled);
		log.trace("words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace("words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace2(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled()).thenReturn(levelIsEnabled);
		log.trace("words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace("words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace3(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled()).thenReturn(levelIsEnabled);
		log.trace("words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace("words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace4(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled()).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.trace("words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace("words", t, mockCtx);
		}
	}

	@Test
	void isTraceEnabled1() {
		Mockito.when(mockLogger.isTraceEnabled(marker)).thenReturn(true);
		Assertions.assertTrue(log.isTraceEnabled(marker));
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace5(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.trace(marker, "words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace(marker, "words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace6(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.trace(marker, "words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace(marker, "words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace7(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.trace(marker, "words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace(marker, "words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace8(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.trace(marker, "words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace(marker, "words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testTrace9(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isTraceEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.trace(marker, "words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).trace(marker, "words", t, mockCtx);
		}
	}

	@Test
	void isDebugEnabled0() {
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(true);
		Assertions.assertTrue(log.isDebugEnabled());
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug0(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(levelIsEnabled);
		log.debug("words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug("words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug1(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(levelIsEnabled);
		log.debug("words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug("words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug2(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(levelIsEnabled);
		log.debug("words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug("words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug3(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(levelIsEnabled);
		log.debug("words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug("words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug4(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled()).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.debug("words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug("words", t, mockCtx);
		}
	}

	@Test
	void isDebugEnabled1() {
		Mockito.when(mockLogger.isDebugEnabled(marker)).thenReturn(true);
		Assertions.assertTrue(log.isDebugEnabled(marker));
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug5(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.debug(marker, "words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug(marker, "words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug6(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.debug(marker, "words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug(marker, "words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug7(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.debug(marker, "words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug(marker, "words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug8(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.debug(marker, "words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug(marker, "words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testDebug9(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isDebugEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.debug(marker, "words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).debug(marker, "words", t, mockCtx);
		}
	}

	@Test
	void isInfoEnabled0() {
		Mockito.when(mockLogger.isInfoEnabled()).thenReturn(true);
		Assertions.assertTrue(log.isInfoEnabled());
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo0(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled()).thenReturn(levelIsEnabled);
		log.info("words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info("words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo1(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled()).thenReturn(levelIsEnabled);
		log.info("words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info("words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo2(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled()).thenReturn(levelIsEnabled);
		log.info("words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info("words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo3(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled()).thenReturn(levelIsEnabled);
		log.info("words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info("words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo4(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled()).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.info("words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info("words", t, mockCtx);
		}
	}

	@Test
	void isInfoEnabled1() {
		Mockito.when(mockLogger.isInfoEnabled(marker)).thenReturn(true);
		Assertions.assertTrue(log.isInfoEnabled(marker));
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo5(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.info(marker, "words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info(marker, "words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo6(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.info(marker, "words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info(marker, "words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo7(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.info(marker, "words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info(marker, "words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo8(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.info(marker, "words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info(marker, "words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testInfo9(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isInfoEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.info(marker, "words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).info(marker, "words", t, mockCtx);
		}
	}

	@Test
	void isWarnEnabled0() {
		Mockito.when(mockLogger.isWarnEnabled()).thenReturn(true);
		Assertions.assertTrue(log.isWarnEnabled());
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn0(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled()).thenReturn(levelIsEnabled);
		log.warn("words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn("words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn1(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled()).thenReturn(levelIsEnabled);
		log.warn("words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn("words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn2(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled()).thenReturn(levelIsEnabled);
		log.warn("words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn("words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn3(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled()).thenReturn(levelIsEnabled);
		log.warn("words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn("words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn4(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled()).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.warn("words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn("words", t, mockCtx);
		}
	}

	@Test
	void isWarnEnabled1() {
		Mockito.when(mockLogger.isWarnEnabled(marker)).thenReturn(true);
		Assertions.assertTrue(log.isWarnEnabled(marker));
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn5(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.warn(marker, "words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn(marker, "words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn6(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.warn(marker, "words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn(marker, "words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn7(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.warn(marker, "words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn(marker, "words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn8(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.warn(marker, "words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn(marker, "words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testWarn9(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isWarnEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.warn(marker, "words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).warn(marker, "words", t, mockCtx);
		}
	}

	@Test
	void isErrorEnabled0() {
		Mockito.when(mockLogger.isErrorEnabled()).thenReturn(true);
		Assertions.assertTrue(log.isErrorEnabled());
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError0(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled()).thenReturn(levelIsEnabled);
		log.error("words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error("words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError1(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled()).thenReturn(levelIsEnabled);
		log.error("words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error("words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError2(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled()).thenReturn(levelIsEnabled);
		log.error("words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error("words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError3(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled()).thenReturn(levelIsEnabled);
		log.error("words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error("words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError4(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled()).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.error("words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error("words", t, mockCtx);
		}
	}

	@Test
	void isErrorEnabled1() {
		Mockito.when(mockLogger.isErrorEnabled(marker)).thenReturn(true);
		Assertions.assertTrue(log.isErrorEnabled(marker));
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError5(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.error(marker, "words");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error(marker, "words", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError6(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.error(marker, "words 1={}", "one");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error(marker, "words 1={}", "one", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError7(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.error(marker, "words 1={} 2={}", "one", "two");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error(marker, "words 1={} 2={}", "one", "two", mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError8(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		log.error(marker, "words 1={} 2={} 3={}", "one", "two", "three");
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error(marker, "words 1={} 2={} 3={}",
					ImmutableList.<String>builder().add("one", "two", "three").build().toArray(), mockCtx);
		}
	}

	@ParameterizedTest
	@MethodSource("provideTrueFalse")
	void testError9(boolean levelIsEnabled) {
		Mockito.when(mockLogger.isErrorEnabled(ArgumentMatchers.any())).thenReturn(levelIsEnabled);
		RuntimeException t = new RuntimeException();
		log.error(marker, "words", t);
		if (levelIsEnabled) {
			Mockito.verify(mockLogger).error(marker, "words", t, mockCtx);
		}
	}

	private static Stream<Arguments> provideTrueFalse() {
		return Stream.of(Arguments.of(true), Arguments.of(false));
	}
}