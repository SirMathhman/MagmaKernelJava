package com.meti.parse;

import com.meti.Parser;
import com.meti.SingletonCompiler;
import com.meti.data.DataStack;
import com.meti.data.TreeStack;
import com.meti.unit.PrimitiveUnit;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeclareParserTest {

	@Test
	void parse() {
		DataStack stack = new TreeStack();
		Parser parser = new DeclareParser(stack);
		String result = parser.parse("val x : Int", SingletonCompiler.onlyResolver(PrimitiveUnit.INT))
				.orElseThrow()
				.build()
				.render();
		assertEquals("int x", result);
		assertTrue(stack.get(Collections.singletonList("x")).isPresent());
	}
}