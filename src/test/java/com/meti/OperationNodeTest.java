package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;
import com.meti.node.transform.operate.OperationNode;
import com.meti.node.transform.operate.Operations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationNodeTest {

	@Test
	void render() {
		Node value0 = new CIntNode(10);
		Node value1 = new CIntNode(20);
		Node node = new OperationNode(value0, Operations.ADD, value1);
		assertEquals("10+20", node.render());
	}
}