package com.cap.backend.common.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class LoggableContextTest {

    @Mock
    private ObjectMapper mapper;

    private LoggableContext loggableContext;
    private ArgumentCaptor<HashMap<String, Object>> capturedContext;

    @BeforeEach
    void setUp() {
        loggableContext = new LoggableContext(mapper);
        capturedContext = ArgumentCaptor.forClass(HashMap.class);
    }

    @Test
    void reset() throws JsonProcessingException {
        loggableContext.add("key", "value");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        Assertions.assertFalse(capturedContext.getValue().isEmpty());
        Mockito.reset(mapper);

        loggableContext.reset();
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        Assertions.assertTrue(capturedContext.getValue().isEmpty());
    }

    @Test
    void asJson_success() throws JsonProcessingException {
        Mockito.when(mapper.writeValueAsString(Mockito.any())).thenReturn("json");
        Assertions.assertEquals("json", loggableContext.asJson());
    }

    @Test
    void asJson_error() throws JsonProcessingException {
        JsonProcessingException error_message = Mockito.mock(JsonProcessingException.class);
        Mockito.when(error_message.getMessage()).thenReturn("error message");
        Mockito.when(mapper.writeValueAsString(Mockito.any())).thenThrow(error_message);
        Assertions.assertEquals("{\"loggable_context_failure\":\"error message\"}", loggableContext.asJson());
    }

    @Test
    void add_simpleKeyValueString() throws JsonProcessingException {
        loggableContext.add("key", "value");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("key"));
        Assertions.assertEquals("value", contextValue.get("key"));
    }

    @Test
    void add_newNestedKey() throws JsonProcessingException {
        loggableContext.add("nested.key", "value");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("nested"));
        Object o = contextValue.get("nested");
        Assertions.assertTrue(o instanceof HashMap);
        HashMap<String, Object> nested = (HashMap<String, Object>) o;
        Assertions.assertTrue(nested.containsKey("key"));
        Assertions.assertEquals("value", nested.get("key"));
    }

    @Test
    void add_existingNestedKey() throws JsonProcessingException {
        loggableContext.add("nested.key", "value1");
        loggableContext.add("nested.key", "value2");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("nested"));
        Object o = contextValue.get("nested");
        Assertions.assertTrue(o instanceof HashMap);
        HashMap<String, Object> nested = (HashMap<String, Object>) o;
        Assertions.assertTrue(nested.containsKey("key"));
        Object l = nested.get("key");
        Assertions.assertTrue(l instanceof ArrayList);
        ArrayList list = (ArrayList) l;
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("value1", list.get(0));
        Assertions.assertEquals("value2", list.get(1));
    }

    @Test
    void add_keyMultipleValues() throws JsonProcessingException {
        loggableContext.add("nested.key", "value1");
        loggableContext.add("nested.key", "value2");
        loggableContext.add("nested.key", "value3");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("nested"));
        Object o = contextValue.get("nested");
        Assertions.assertTrue(o instanceof HashMap);
        HashMap<String, Object> m = (HashMap<String, Object>) o;
        Assertions.assertTrue(m.containsKey("key"));
        Object key = m.get("key");
        Assertions.assertTrue(key instanceof ArrayList);
        ArrayList l = (ArrayList) key;
        Assertions.assertEquals(3, l.size());
        Assertions.assertEquals("value1", l.get(0));
        Assertions.assertEquals("value2", l.get(1));
        Assertions.assertEquals("value3", l.get(2));
    }

    @Test
    void add_nestedMultipleKeys() throws JsonProcessingException {
        loggableContext.add("nested", "value");
        loggableContext.add("nested.key1", "nested_value1");
        loggableContext.add("nested.key2", "nested_value2");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("nested"));
        Object o = contextValue.get("nested");
        Assertions.assertTrue(o instanceof ArrayList);
        ArrayList l = (ArrayList) o;
        Assertions.assertEquals(2, l.size());
        Assertions.assertEquals("value", l.get(0));
        Object m = l.get(1);
        Assertions.assertTrue(m instanceof HashMap);
        HashMap<String, Object> map = (HashMap<String, Object>) m;
        Assertions.assertTrue(map.containsKey("key1"));
        Assertions.assertEquals("nested_value1", map.get("key1"));
        Assertions.assertTrue(map.containsKey("key2"));
        Assertions.assertEquals("nested_value2", map.get("key2"));
    }

    @Test
    void add_deeperNestedKey() throws JsonProcessingException {
        loggableContext.add("nested.key", "value1");
        loggableContext.add("nested.key", "value2");
        loggableContext.add("nested.key.deeper", "value3");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("nested"));
        Object o = contextValue.get("nested");
        Assertions.assertTrue(o instanceof HashMap);
        HashMap<String, Object> nested = (HashMap<String, Object>) o;
        Assertions.assertTrue(nested.containsKey("key"));
        Object l = nested.get("key");
        Assertions.assertTrue(l instanceof ArrayList);
        ArrayList list = (ArrayList) l;
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("value1", list.get(0));
        Assertions.assertEquals("value2", list.get(1));
        Object m = list.get(2);
        Assertions.assertTrue(m instanceof HashMap);
        HashMap<String, Object> map = (HashMap<String, Object>) m;
        Assertions.assertTrue(map.containsKey("deeper"));
        Assertions.assertEquals("value3", map.get("deeper"));
    }

    @Test
    void add_existingDeeperNestedKey() throws JsonProcessingException {
        loggableContext.add("nested.key", "value1");
        loggableContext.add("nested.key", "value2");
        loggableContext.add("nested.key.deeper", "value3");
        loggableContext.add("nested.key.deeper", "value4");
        loggableContext.asJson();

        Mockito.verify(mapper).writeValueAsString(capturedContext.capture());
        HashMap<String, Object> contextValue = capturedContext.getValue();
        Assertions.assertTrue(contextValue.containsKey("nested"));
        Object o = contextValue.get("nested");
        Assertions.assertTrue(o instanceof HashMap);
        HashMap<String, Object> nested = (HashMap<String, Object>) o;
        Assertions.assertTrue(nested.containsKey("key"));
        Object l = nested.get("key");
        Assertions.assertTrue(l instanceof ArrayList);
        ArrayList list = (ArrayList) l;
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("value1", list.get(0));
        Assertions.assertEquals("value2", list.get(1));
        Object m = list.get(2);
        Assertions.assertTrue(m instanceof HashMap);
        HashMap<String, Object> map = (HashMap<String, Object>) m;
        Assertions.assertTrue(map.containsKey("deeper"));
        Object deeper = map.get("deeper");
        Assertions.assertTrue(deeper instanceof ArrayList);
        ArrayList deeperList = (ArrayList) deeper;
        Assertions.assertEquals(2, deeperList.size());
        Assertions.assertEquals("value3", deeperList.get(0));
        Assertions.assertEquals("value4", deeperList.get(1));
    }
}