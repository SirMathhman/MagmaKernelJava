package com.meti.instance;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.meti.Compiler;
import com.meti.InjectedCompiler;
import com.meti.Resolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StructureResolverTest {

	@Test
	void resolveName() {
		Injector injector = Guice.createInjector();
		Compiler compiler = new InjectedCompiler(injector, List.of(
				IntResolver.class
		));
		Resolver resolver = new StructureResolver();
		String type = resolver.resolveName("(Int,Int) => Int", compiler)
				.orElseThrow()
				.toType()
				.render();
		assertEquals("int (*)(int,int)", type);
	}
}