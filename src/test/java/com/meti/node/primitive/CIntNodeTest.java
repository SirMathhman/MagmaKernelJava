package com.meti.node.primitive;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CIntNodeTest {
	@Test
	void test10() {
		Node node = new CIntNode(10);
		assertEquals("10", node.render());
	}

	@Test
	void test100() {
		Node node = new CIntNode(100);
		assertEquals("100", node.render());
	}

}
