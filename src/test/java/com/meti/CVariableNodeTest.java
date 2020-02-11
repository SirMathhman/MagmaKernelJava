package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CVariableNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CVariableNodeTest {

	@Test
	void render() {
		Node node = new CVariableNode("test");
		assertEquals("test", node.render());
	}
}