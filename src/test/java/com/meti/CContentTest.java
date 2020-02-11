package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;
import com.meti.node.primitive.strings.CStringNode;
import com.meti.node.struct.BlockNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CContentTest {

	@Test
	void render() {
		Node value0 = new CIntNode(10);
		Node value1 = new CStringNode("test");
		Node node = new BlockNode(List.of(value0, value1));
		assertEquals("{10\"test\"}", node.render());
	}
}