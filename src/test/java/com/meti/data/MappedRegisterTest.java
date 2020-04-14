package com.meti.data;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MappedRegisterTest {

	@Test
	void has() {
		Map<String, Object> map = Map.of("test", "value");
		Register register = new MappedRegister(map);
		assertTrue(register.has("test"));
	}

	@Test
	void peek() {
		Map<String, Object> map = Map.of("test", "value");
		Register register = new MappedRegister(map);
		assertEquals("value", register.peek("test", String.class).orElseThrow());
		assertTrue(register.has("test"));
	}

	@Test
	void poll() {
		Map<String, Object> map = new HashMap<>();
		map.put("test", "value");
		Register register = new MappedRegister(map);
		assertEquals("value", register.poll("test", String.class).orElseThrow());
		assertFalse(register.has("test"));
	}

	@Test
	void set() {
		Register register = new MappedRegister();
		register.set("test", "value");
		assertEquals("value", register.poll("test", String.class).orElseThrow());
	}
}