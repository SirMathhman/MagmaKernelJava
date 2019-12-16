package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnNodeFactoryTest {

	@Test
	void parse() {
		var result = new ReturnNodeFactory()
				.parse("return 5", new FactoryParser(new PrimitiveNodeFactory()))
				.orElseThrow()
				.compile(new IncrementAliaser());
		assertEquals("return 5;", result);
	}
}