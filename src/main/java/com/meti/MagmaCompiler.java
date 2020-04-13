package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.meti.data.DataModule;
import com.meti.parse.block.*;
import com.meti.parse.external.ImportParser;
import com.meti.parse.operate.CastParser;
import com.meti.parse.operate.OperationParser;
import com.meti.parse.operate.QuantityParser;
import com.meti.parse.store.*;
import com.meti.primitive.*;

class MagmaCompiler extends UnitCompiler {

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

	private MagmaCompiler(Injector injector, Class<?>... classes) {
		super(injector, classes);
	}
}
