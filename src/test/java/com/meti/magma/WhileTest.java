package com.meti.magma;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhileTest extends MagmaTest {
	@Test
	void whileTest() {
		String content = """
			while(true){
				val x = 10;
			}
		""";
		String result = compile(content);
		assertEquals("while(true){int a0=10;}", result);
	}
}
