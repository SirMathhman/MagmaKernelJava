package com.meti;

import com.meti.exception.ParseException;
import com.meti.primitive.IntParser;
import com.meti.primitive.VoidType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvocationParserTest {

	@Test
	void parse() throws ParseException {
		Declarations declarations = new Declarations();
		declarations.define(new FunctionType(Collections.emptySet(), VoidType.INSTANCE), "a");
		Parser parser = new ParentParser(
				new InvocationParser(),
				new VariableParser(declarations),
				new IntParser()
		);
		Resolver resolver = new ParentResolver(
		);
		Compiler compiler = new Compiler(parser, resolver);
		Node node = compiler.parse("a(10)");
		assertEquals("a(10i)", node.render());
	}
}