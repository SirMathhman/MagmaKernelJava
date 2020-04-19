package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.MagmaCompiler.INSTANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCompilerTest {
	@Test
	void testAssign() {
		Compiler instance = INSTANCE.get();
		instance.parse("val x = 10");
		Node result = instance.parse("x = 20");
		assertEquals("x=20;", result.render());
	}

	@Test
	void testDeclare() {
		Node result = INSTANCE.get().parse("val x : Int");
		assertEquals("int x;", result.render());
	}

	@Test
	void testDeclareWithValue() {
		Node result = INSTANCE.get().parse("val x : Int = 10");
		assertEquals("int x=10;", result.render());
	}

	@Test
	void testDeclareWithoutType() {
		Node result = INSTANCE.get().parse("val x = 10");
		assertEquals("int x=10;", result.render());
	}

	@Test
	void testInt() {
		Node result = INSTANCE.get().parse("10");
		assertEquals("10", result.render());
	}

	@Test
	void testResolveBlock() {
		Type type = INSTANCE.get().resolveName("(Int) => Int");
		assertEquals("int (*)(int)", type.render());
	}

	@Test
	void testResolveBlockNoParams() {
		Type type = INSTANCE.get().resolveName("=> Int");
		assertEquals("int (*)()", type.render());
	}

	@Test
	void testResolverBlockNoReturn() {
		Type type = INSTANCE.get().resolveName("(Int)");
		assertEquals("void (*)(int)", type.render());
	}

	@Test
	void testVariable() {
		Compiler instance = INSTANCE.get();
		instance.parse("val x = 10");
		Node result = instance.parse("x");
		assertEquals("x", result.render());
	}
}