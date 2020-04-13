package com.meti;

import com.google.inject.Injector;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.Resolver;
import com.meti.parse.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UnitCompiler implements Compiler {
	private final Collection<Parser> parsers = new ArrayList<>();
	private final Collection<Resolver> resolvers = new ArrayList<>();

	public UnitCompiler(Injector injector, Class<?>... classes) {
		for (Class<?> aClass : classes) {
			Object c = injector.getInstance(aClass);
			if (c instanceof Parser) parsers.add((Parser) c);
			if (c instanceof Resolver) resolvers.add((Resolver) c);
		}
	}

	@Override
	public Node parse(String content) {
		return parsers.stream()
				.map(parser -> parseImpl(content, parser))
				.flatMap(Optional::stream)
				.findFirst()
				.orElseThrow(() -> cannot(content));
	}

	private Optional<Node> parseImpl(String content, Parser parser) {
		try {
			return parser.parse(content, this);
		} catch (Exception e) {
			throw new CompileException("Failed to parse: " + content, e);
		}
	}

	private IllegalArgumentException cannot(String value) {
		return new IllegalArgumentException("Cannot parse: " + value);
	}

	@Override
	public Type resolveName(String content) {
		return resolvers.stream()
				.map(resolver -> resolver.resolveName(content, this))
				.flatMap(Optional::stream)
				.findFirst()
				.orElseThrow(() -> cannot(content));
	}

	@Override
	public Type resolveValue(String content) {
		return resolvers.stream()
				.map(resolver -> resolver.resolveValue(content, this))
				.flatMap(Optional::stream)
				.findFirst()
				.orElseThrow(() -> cannot(content));
	}
}
