package com.meti;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclareNodeTest {

	@Test
	void withInit() {
		Node node = new DeclareNode("name", PrimitiveType.INT, new IntNode(10));
		Collection<CacheUpdate> cacheUpdates = node.toUpdates();
		assertEquals(1, cacheUpdates.size());
		CacheUpdate update = (CacheUpdate) cacheUpdates.toArray()[0];
		String result = update.render(new ArrayList<>());
		assertEquals("int name=10;", result);
	}

	@Test
	void withoutInit() {
		Node node = new DeclareNode("name", PrimitiveType.INT);
		Collection<CacheUpdate> cacheUpdates = node.toUpdates();
		assertEquals(1, cacheUpdates.size());
		CacheUpdate update = (CacheUpdate) cacheUpdates.toArray()[0];
		String result = update.render(new ArrayList<>());
		assertEquals("int name;", result);
	}
}