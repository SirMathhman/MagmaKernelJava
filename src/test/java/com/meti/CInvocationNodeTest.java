package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CVariableNode;
import com.meti.node.struct.invoke.CInvocationNode;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CInvocationNodeTest {

	@Test
	void render() {
		Node a = new CVariableNode("a");
		Node b = new CVariableNode("b");
		Node node = new CInvocationNode(a, Collections.singletonList(b));
		assertEquals("a(b)", node.render());
	}
}