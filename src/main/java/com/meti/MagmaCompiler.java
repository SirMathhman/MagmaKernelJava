package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MagmaCompiler extends UnitCompiler {

	public MagmaCompiler() {
		this(Guice.createInjector(new DataModule()),
				StructParser.class,
				DeclareParser.class,
				ReturnParser.class,
				InvocationParser.class,
				StringParser.class,
				IntParser.class,
				ReferenceParser.class,
				ThisParser.class,
				ImportParser.class,
				VariableParser.class,
				StructResolver.class,
				IntResolver.class,
				VoidResolver.class,
				VariableResolver.class);
	}

	public MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
