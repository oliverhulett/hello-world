package com.cap.backend.config;

import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeZoneConfig {

	static {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
	}
}
