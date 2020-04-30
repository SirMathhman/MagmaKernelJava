package com.meti.unit;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.SingletonCompiler;
import com.meti.data.DataStack;
import com.meti.data.TreeStack;
import com.meti.resolve.Instance;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockUnitTest {

	@Test
	void parse() {
		DataStack stack = new TreeStack();
		Parser unit = new BlockUnit(stack);
		Compiler compiler = SingletonCompiler.onlyResolver(PrimitiveUnit.INT);
		String result = unit.parse("(x : Int) => Int {}", compiler)
				.orElseThrow()
				.build()
				.render();
		assertEquals("_0", result);
	}

	@Test
	void parseParameters() {
		DataStack stack = new TreeStack();
		BlockUnit unit = new BlockUnit(stack);
		Compiler compiler = SingletonCompiler.onlyResolver(PrimitiveUnit.INT);
		Map<String, Instance> parameters = unit.parseParameters("x : Int", compiler);
		assertEquals(1, parameters.size());
		assertEquals("int", parameters.get("x")
				.build()
				.render()
				.trim());
	}

	@Test
	void parseReturnInstance() {
		DataStack stack = new TreeStack();
		BlockUnit unit = new BlockUnit(stack);
		Compiler compiler = SingletonCompiler.onlyResolver(PrimitiveUnit.INT);
		Instance instance = unit.parseReturnInstance("=> Int", compiler);
		assertEquals("int", instance.build()
				.render()
				.trim());
	}

	@Test
	void resolveName() {
	}

	@Test
	void resolveValue() {
	}

	@Test
	void testParseReturnInstance() {
		DataStack stack = new TreeStack();
		BlockUnit unit = new BlockUnit(stack);
		Compiler compiler = SingletonCompiler.onlyResolver(PrimitiveUnit.INT);
		Instance instance = unit.parseReturnInstance(") => Int {", compiler, 0);
		assertEquals("int", instance.build()
				.render()
				.trim());
	}
}