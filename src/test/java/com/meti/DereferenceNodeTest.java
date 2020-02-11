package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CVariableNode;
import com.meti.node.point.DereferenceNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DereferenceNodeTest {

	@Test
	void render() {
		Node value = new CVariableNode("test");
		Node node = new DereferenceNode(value);
		assertEquals("*test", node.render());
	}
}