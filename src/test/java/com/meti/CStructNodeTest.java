package com.meti;

import com.meti.node.Node;
import com.meti.node.Parameter;
import com.meti.node.primitive.ints.IntType;
import com.meti.node.struct.CStructNode;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CStructNodeTest {

	@Test
	void render() {
		Parameter x = Parameter.create(IntType.INSTANCE, Collections.singletonList("x"));
		Parameter y = Parameter.create(IntType.INSTANCE, Collections.singletonList("y"));
		Collection<Parameter> parameters = List.of(x, y);
		Node node = new CStructNode("Point", parameters);
		assertEquals("struct Point{int x;int y;};", node.render());
	}
}