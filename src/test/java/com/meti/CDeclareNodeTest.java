package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CDeclareNode;
import com.meti.node.primitive.ints.IntNode;
import com.meti.node.primitive.ints.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CDeclareNodeTest {

	@Test
	void render() {
		Node value = new IntNode(10);
		Node node = new CDeclareNode(IntType.INSTANCE, "test", value);
		assertEquals("int test=10;", node.render());
	}
}