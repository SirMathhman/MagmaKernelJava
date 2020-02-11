package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;
import com.meti.node.struct.ReturnNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnNodeTest {

	@Test
	void render() {
		Node value = new CIntNode(10);
		Node node = new ReturnNode(value);
		assertEquals("return 10;", node.render());
	}
}