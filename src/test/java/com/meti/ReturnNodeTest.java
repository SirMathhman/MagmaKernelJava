package com.meti;

import com.meti.primitive.IntNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnNodeTest {

	@Test
	void render() {
		Node value = new IntNode(10);
		Node node = new ReturnNode(value);
		assertEquals("return 10i;", node.render());
	}
}