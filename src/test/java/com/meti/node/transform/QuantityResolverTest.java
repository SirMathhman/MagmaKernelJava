package com.meti.node.transform;

import com.meti.Compiler;
import com.meti.Resolver;
import com.meti.node.Type;
import com.meti.node.primitive.ints.IntResolver;
import com.meti.node.primitive.ints.IntType;
import com.meti.util.UnitCompiler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuantityResolverTest {

	@Test
	void resolveValue() {
		Resolver resolver = new QuantityResolver();
		Compiler compiler = new UnitCompiler(null, new IntResolver());
		Type type = resolver.resolveValue("(10)", compiler)
				.orElseThrow();
		assertEquals(IntType.INSTANCE, type);
	}
}