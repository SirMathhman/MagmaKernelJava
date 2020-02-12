package com.meti;

import com.meti.node.Node;
import com.meti.node.declare.CDeclareNode;
import com.meti.node.primitive.ints.CIntNode;
import com.meti.node.primitive.ints.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CDeclareTest {

	@Test
	void render() {
		Node value = new CIntNode(10);
		Node node = new CDeclareNode(IntType.INSTANCE, "test", value);
		assertEquals("int test=10;", node.render());
	}
}