package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.MagmaCompiler.INSTANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void testDeclare() {
		Node result = INSTANCE.parse("val x : Int");
		assertEquals("int x", result.render());
	}

	@Test
	void testDeclareWithValue() {
		Node result = INSTANCE.parse("val x : Int = 10");
		assertEquals("int x=10", result.render());
	}

	@Test
	void testInt() {
		Node result = INSTANCE.parse("10");
		assertEquals("10", result.render());
	}
}