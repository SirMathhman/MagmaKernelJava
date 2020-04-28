package com.meti.unit;

import com.meti.Parser;
import com.meti.SingletonCompiler;
import com.meti.data.DataStack;
import com.meti.data.TreeStack;
import com.meti.parse.DeclareParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableUnitTest {

	@Test
	void parse() {
		DataStack stack = new TreeStack();
		Parser before = new DeclareParser(stack);
		before.parse("val x : Int", SingletonCompiler.onlyResolver(PrimitiveUnit.INT));
		Parser parser = new VariableUnit(stack);
		String result = parser.parse("x", null)
				.orElseThrow()
				.build()
				.render();
		assertEquals("x", result);
	}
}