package com.meti.node.transform;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.node.Node;
import com.meti.node.primitive.ints.IntParser;
import com.meti.util.UnitCompiler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuantityParserTest {

	@Test
	void parse() {
		Compiler compiler = new UnitCompiler(new IntParser(), null);
		Parser parser = new QuantityParser();
		String result = parser.parse("(10)", compiler)
				.map(Node::render)
				.orElseThrow();
		assertEquals("(10)", result);
	}
}