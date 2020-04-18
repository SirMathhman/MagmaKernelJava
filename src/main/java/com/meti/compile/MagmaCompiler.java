package com.meti.compile;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MagmaCompiler extends UnitCompiler {
	public static final Compiler INSTANCE = new MagmaCompiler();

	private MagmaCompiler() {
		this(Guice.createInjector(new DataModule()),
				DeclareParser.class,
				IntUnit.class);
	}

	private MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
