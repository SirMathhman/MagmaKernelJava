package com.meti.compile;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.function.Supplier;

class MagmaCompiler extends UnitCompiler {
	static final Supplier<Compiler> INSTANCE = MagmaCompiler::new;

	private MagmaCompiler() {
		this(Guice.createInjector(new DataModule()),
				DeclareParser.class,
				AssignParser.class,
				IntUnit.class,
				VariableParser.class);
	}

	private MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
