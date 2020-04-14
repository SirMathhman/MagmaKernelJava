package com.meti.data;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MappedCacheTest {

	@Test
	void append() {
		Map<Integer, List<String>> map = new HashMap<>();
		Cache cache = new MappedCache(map);
		cache.append(0, "a");
		cache.append(1, "b");
		assertIterableEquals(singleton("a"), map.get(0));
		assertIterableEquals(singleton("b"), map.get(1));
	}

	@Test
	void render() {
		Cache cache = new MappedCache();
		cache.append(0, "a");
		cache.append(1, "b");
		assertEquals("ab", cache.render());
	}
}