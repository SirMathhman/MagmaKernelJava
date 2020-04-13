package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MagmaCompiler extends UnitCompiler {

	public MagmaCompiler() {
		this(Guice.createInjector(new DataModule()),
				StructParser.class);
	}

	public MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
