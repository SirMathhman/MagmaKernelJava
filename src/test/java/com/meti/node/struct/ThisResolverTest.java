package com.meti.node.struct;

import com.meti.Resolver;
import com.meti.node.Type;
import com.meti.node.struct.type.FunctionTypeBuilder;
import com.meti.parse.Declarations;
import com.meti.parse.TreeDeclarations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThisResolverTest {

	@Test
	void resolveValue() {
		Declarations declarations = new TreeDeclarations();
		declarations.define(FunctionTypeBuilder.create().build(), "test");
		Resolver resolver = new ThisResolver(declarations);
		Type type = declarations.inStack("test", s -> resolver.resolveValue("this", null))
				.orElseThrow();
		assertEquals("struct test", type.render());
	}
}