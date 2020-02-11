package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;
import com.meti.node.struct.CReturnNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CReturnNodeTest {

	@Test
	void render() {
		Node value = new CIntNode(10);
		Node node = new CReturnNode(value);
		assertEquals("return 10;", node.render());
	}
}