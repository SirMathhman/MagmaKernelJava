package com.meti.node.primitive;

import com.meti.node.Node;
import com.meti.node.primitive.floats.CFloatNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CFloatNodeTest {
	@Test
	void render10f() {
		Node node = new CFloatNode(10);
		assertEquals("10.0", node.render());
	}

	@Test
	void render14f() {
		Node node = new CFloatNode(1.4f);
		assertEquals("1.4", node.render());
	}
}