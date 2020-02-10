package com.meti;

import com.meti.node.Node;
import com.meti.node.primitive.ints.IntParser;
import com.meti.node.primitive.ints.IntResolver;
import com.meti.node.primitive.ints.IntType;
import com.meti.node.struct.ReturnParser;
import com.meti.node.struct.type.FunctionType;
import com.meti.node.struct.type.FunctionTypeBuilder;
import com.meti.parse.Declarations;
import com.meti.parse.TreeDeclarations;
import com.meti.util.ParentParser;
import com.meti.util.UnitCompiler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnParserTest {

	@Test
	void parse() {
		Declarations declarations = new TreeDeclarations();
		FunctionType type = FunctionTypeBuilder.create()
				.withReturnType(IntType.INSTANCE)
				.build();
		declarations.define(type, "test");
		Compiler compiler = new UnitCompiler(new ParentParser(
				new ReturnParser(declarations),
				new IntParser()),
				new IntResolver());
		Node node = declarations.inStack("test", s -> compiler.parse("return 10"));
		assertEquals("return 10;", node.render());
	}
}