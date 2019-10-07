package com.cap.backend.common.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cap.backend.config.JacksonJsonConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggableContext {

	private final Map<String, Object> context = new HashMap<>();
	private final ObjectMapper mapper;

	public LoggableContext() {
		this(new ObjectMapper());
	}

	LoggableContext(ObjectMapper mapper) {
		this.mapper = mapper;
		JacksonJsonConfig.configureMapper(mapper);
	}

	// Helper method to allow callers to borrow the context's object mapper.
	public String object2Json(Object value) throws JsonProcessingException {
		return mapper.writeValueAsString(value);
	}

	public void reset() {
		context.clear();
	}

	public void add(String key, String value) {
		insertIntoMap(context, new Key(key), value);
	}

	String asJson() {
		try {
			return mapper.writeValueAsString(context);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{\"loggable_context_failure\":\"" + e.getMessage() + "\"}";
		}
	}

	private void insertIntoMap(Map<String, Object> m, Key k, String v) {
		Object o = m.get(k.front);
		if (k.remainder == null) {
			if (o == null) {
				// Insert `v` here, there is no current value.
				m.put(k.front, v);
			} else {
				// Insert `v` here, next to the existing value(s).
				if (o instanceof List) {
					((List) o).add(v);
				} else {
					ArrayList l = new ArrayList();
					l.add(o);
					l.add(v);
					m.put(k.front, l);
				}
			}
		} else {
			if (o == null) {
				// Create new map to continue building context key.
				HashMap<String, Object> h = new HashMap<>();
				m.put(k.front, h);
				insertIntoMap(h, new Key(k.remainder), v);
			} else {
				if (o instanceof Map) {
					// Continue walking map for our context key.
					insertIntoMap((Map<String, Object>) o, new Key(k.remainder), v);
				} else if (o instanceof List) {
					// Walk the list looking for a map to continue walking for
					// our context key.
					boolean inserted = false;
					for (Object h : (List) o) {
						if (h instanceof Map) {
							insertIntoMap((Map<String, Object>) h, new Key(k.remainder), v);
							inserted = true;
							break;
						}
					}
					if (!inserted) {
						HashMap<String, Object> h = new HashMap<>();
						insertIntoMap(h, new Key(k.remainder), v);
						((List) o).add(h);
					}
				} else {
					// Turn `o` into a list of `o` and a new map to continue our
					// context key.
					ArrayList l = new ArrayList();
					l.add(o);
					HashMap<String, Object> h = new HashMap<>();
					l.add(h);
					insertIntoMap(h, new Key(k.remainder), v);
					m.put(k.front, l);
				}
			}
		}
	}

	private static class Key {
		final String front;
		final String remainder;

		Key(String k) {
			String[] split = k.split("\\.", 2);
			front = split[0];
			if (split.length > 1) {
				remainder = split[1];
			} else {
				remainder = null;
			}
		}
	}
}
