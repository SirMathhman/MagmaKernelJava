package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;
import com.meti.node.transform.CQuantityNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CQuantityNodeTest {

	@Test
	void render() {
		Node value = new CIntNode(10);
		Node node = new CQuantityNode(value);
		assertEquals("(10)", node.render());
	}
}