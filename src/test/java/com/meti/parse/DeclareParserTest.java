package com.meti.parse;

import com.meti.Parser;
import com.meti.SingletonCompiler;
import com.meti.data.DataStack;
import com.meti.data.MutableStack;
import com.meti.unit.PrimitiveUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclareParserTest {

	@Test
	void parse() {
		DataStack stack = new MutableStack();
		Parser parser = new DeclareParser(stack);
		String result = parser.parse("val x : Int", SingletonCompiler.onlyResolver(PrimitiveUnit.INT))
				.orElseThrow()
				.build()
				.render();
		assertEquals("int x", result);
	}
}