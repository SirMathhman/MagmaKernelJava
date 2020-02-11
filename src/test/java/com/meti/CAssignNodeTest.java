package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CAssignNode;
import com.meti.node.declare.CVariableNode;
import com.meti.node.primitive.ints.CIntNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CAssignNodeTest {

	@Test
	void render() {
		Node from = new CIntNode(10);
		Node to = new CVariableNode("x");
		Node node = new CAssignNode(to, from);
		assertEquals("x=10;", node.render());
	}
}