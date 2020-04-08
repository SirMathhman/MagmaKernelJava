package com.meti;

import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class InjectedCompiler implements Compiler {
	private final Collection<Parser> parsers = new ArrayList<>();
	private final Collection<Resolver> resolvers = new ArrayList<>();

	public InjectedCompiler(Injector injector, Iterable<Class<?>> classes) {
		for (Class<?> aClass : classes) {
			Object instance = injector.getInstance(aClass);
			if (instance instanceof Parser) parsers.add((Parser) instance);
			if (instance instanceof Resolver) resolvers.add((Resolver) instance);
		}
	}

	@Override
	public Node parse(String content) {
		return parsers.stream()
				.map(parser -> parser.parse(content, this))
				.flatMap(Optional::stream)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Failed to parse: " + content));
	}

	@Override
	public Instance resolveName(String content) {
		return resolvers.stream()
				.map(resolver -> resolver.resolveName(content, this))
				.flatMap(Optional::stream)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Failed to resolve name: " + content));
	}

	@Override
	public Instance resolveValue(String content) {
		return resolvers.stream()
				.map(resolver -> resolver.resolveValue(content, this))
				.flatMap(Optional::stream)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Failed to resolve value: " + content));
	}
}
