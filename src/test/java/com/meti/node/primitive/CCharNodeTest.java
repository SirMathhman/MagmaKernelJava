package com.meti.node.primitive;

import com.meti.node.Node;
import com.meti.node.primitive.chars.CCharNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CCharNodeTest {

	@Test
	void render() {
		Node node = new CCharNode('a');
		assertEquals("'a'", node.render());
	}
}