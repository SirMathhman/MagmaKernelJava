package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.strings.CStringNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CStringNodeTest {
	@Test
	void test() {
		Node node = new CStringNode("Hello World!");
		assertEquals("\"Hello World!\"", node.render());
	}
}
