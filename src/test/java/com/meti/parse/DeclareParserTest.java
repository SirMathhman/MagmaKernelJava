package com.meti.parse;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.meti.Compiler;
import com.meti.InjectedCompiler;
import com.meti.Node;
import com.meti.instance.IntResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeclareParserTest {

	@Test
	void parse() {
		Injector injector = Guice.createInjector();
		Compiler compiler = new InjectedCompiler(injector, List.of(DeclareParser.class,
				IntResolver.class));
		Node node = compiler.parse("val x : Int");
		String result = node.renderJoined();
		assertEquals("int x;", result);
	}
}