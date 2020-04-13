package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MagmaCompiler extends UnitCompiler {

	public MagmaCompiler() {
		this(Guice.createInjector(new DataModule()),
				QuantityParser.class,
				StructParser.class,
				CastParser.class,
				DeclareParser.class,
				ReturnParser.class,
				OperationParser.class,
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
				StringResolver.class,
				FloatResolver.class,
				PointerResolver.class,
				VariableResolver.class);
	}

	public MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
