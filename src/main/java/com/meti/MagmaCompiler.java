package com.meti;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.meti.instance.CharResolver;
import com.meti.instance.DoubleResolver;
import com.meti.instance.IntResolver;
import com.meti.instance.StructureResolver;
import com.meti.parse.BlockParser;
import com.meti.parse.DeclareParser;
import com.meti.parse.IntParser;

import java.util.Collections;
import java.util.List;

public class MagmaCompiler extends InjectedCompiler {
	public MagmaCompiler() {
		//TODO: simplify class
		this(Collections.singleton(new DataModule()), List.of(
				StructureResolver.class,
				CharResolver.class,
				DoubleResolver.class,
				IntResolver.class,
				BlockParser.class,
				DeclareParser.class,
				IntParser.class
		));
	}

	private MagmaCompiler(Iterable<? extends Module> modules, Iterable<Class<?>> classes) {
		this(Guice.createInjector(modules), classes);
	}

	private MagmaCompiler(Injector injector, Iterable<Class<?>> classes) {
		super(injector, classes);
	}
}
