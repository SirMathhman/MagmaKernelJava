package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CVariableNode;
import com.meti.node.point.CReferenceNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CReferenceNodeTest {

	@Test
	void render() {
		Node value = new CVariableNode("test");
		Node node = new CReferenceNode(value);
		assertEquals("&test", node.render());
	}
}