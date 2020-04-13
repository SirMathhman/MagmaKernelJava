package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MagmaCompiler extends UnitCompiler {

	public MagmaCompiler() {
		this(Guice.createInjector(new DataModule()),
				DeclareParser.class,
				StructParser.class,
				ReturnParser.class,
				InvocationParser.class,
				IntParser.class,
				ReferenceParser.class,
				ThisParser.class,
				VariableParser.class,
				StructResolver.class,
				IntResolver.class);
	}

	public MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
