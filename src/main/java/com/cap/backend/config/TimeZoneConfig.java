package com.cap.backend.config;

import java.time.ZoneOffset;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeZoneConfig {

	static {
		DateTimeZone.setDefault(DateTimeZone.UTC);
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
	}
}
