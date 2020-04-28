package com.meti;

import com.meti.parse.Item;
import com.meti.resolve.Instance;

import java.util.Collection;
import java.util.Optional;

public class CompoundUnit implements Unit {
	private final Parser parser;
	private final Resolver resolver;

	public CompoundUnit(Collection<? extends Unit> units) {
		this(units, units);
	}

	public CompoundUnit(Collection<? extends Parser> parsers, Collection<? extends Resolver> resolvers) {
		this(new CompoundParser(parsers), new CompoundResolver(resolvers));
	}

	public CompoundUnit(Parser parser, Resolver resolver) {
		this.parser = parser;
		this.resolver = resolver;
	}

	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		return parser.parse(content, compiler);
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return resolver.resolveName(content, compiler);
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return resolver.resolveValue(content, compiler);
	}
}
