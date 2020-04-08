package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.meti.instance.*;
import com.meti.parse.*;

import java.util.Collections;
import java.util.List;

public class MagmaCompiler extends InjectedCompiler {
	public MagmaCompiler() {
		//TODO: simplify class
		this(Collections.singleton(new DataModule()), List.of(
				StructureResolver.class,
				CharResolver.class,
				DoubleResolver.class,
				VariableResolver.class,
				IntResolver.class,
				BlockParser.class,
				StructureParser.class,
				AssignParser.class,
				DeclareParser.class,
				IntParser.class,
				VariableParser.class
		));
	}

	private MagmaCompiler(Iterable<? extends Module> modules, Iterable<Class<?>> classes) {
		this(Guice.createInjector(modules), classes);
	}

	private MagmaCompiler(Injector injector, Iterable<Class<?>> classes) {
		super(injector, classes);
	}
}
