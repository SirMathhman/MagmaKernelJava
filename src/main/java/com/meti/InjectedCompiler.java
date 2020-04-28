package com.meti;

import com.google.inject.Injector;
import com.meti.parse.Item;
import com.meti.resolve.Instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InjectedCompiler implements Compiler {
	private final Parser parser;
	private final Resolver resolver;

	private InjectedCompiler(Iterable<?> instances) {
		Collection<Parser> parsers = new ArrayList<>();
		Collection<Resolver> resolvers = new ArrayList<>();
		for (Object instance : instances) {
			if (instance instanceof Parser) parsers.add((Parser) instance);
			if (instance instanceof Resolver) resolvers.add((Resolver) instance);
		}
		this.parser = new CompoundParser(parsers);
		this.resolver = new CompoundResolver(resolvers);
	}

	public static Compiler from(Injector injector, Class<?>... classes) {
		List<?> instances = Arrays.stream(classes)
				.map(injector::getInstance)
				.collect(Collectors.toList());
		return new InjectedCompiler(instances);
	}

	@Override
	public Item parse(String content) {
		return parser.parse(content, this)
				.orElseThrow(() -> fail("Failed to parse: " + content));
	}

	private RuntimeException fail(String message) {
		return new CompileException(message);
	}

	@Override
	public Instance resolveName(String content) {
		return resolver.resolveName(content, this)
				.orElseThrow(() -> fail("Failed to resolve name: " + content));
	}

	@Override
	public Instance resolveValue(String content) {
		return resolver.resolveValue(content, this)
				.orElseThrow(() -> fail("Failed to resolve value: " + content));
	}
}
