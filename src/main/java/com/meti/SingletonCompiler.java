package com.meti;

import com.meti.parse.Item;
import com.meti.resolve.Instance;

public class SingletonCompiler implements Compiler {
	private final Parser parser;
	private final Resolver resolver;

	public SingletonCompiler(Parser parser, Resolver resolver) {
		this.parser = parser;
		this.resolver = resolver;
	}

	public static Compiler onlyParser(Parser parser) {
		return new SingletonCompiler(parser, null);
	}

	public static Compiler onlyResolver(Resolver resolver) {
		return new SingletonCompiler(null, resolver);
	}

	@Override
	public Item parse(String content) {
		if (null == parser) {
			throw new IllegalStateException("No parser was given.");
		} else {
			return parser.parse(content, this)
					.orElseThrow(() -> new IllegalArgumentException("Parser did not return a result for: " + content));
		}
	}

	@Override
	public Instance resolveName(String content) {
		if (null == resolver) {
			throw new IllegalStateException("No resolver was given.");
		} else {
			return resolver.resolveName(content, this)
					.orElseThrow(() -> new IllegalArgumentException("Resolver did not return a name for: " + content));
		}
	}

	@Override
	public Instance resolveValue(String content) {
		if (null == resolver) {
			throw new IllegalStateException("No resolver was given.");
		} else {
			return resolver.resolveValue(content, this)
					.orElseThrow(() -> new IllegalArgumentException("Resolver did not return a value for: " + content));
		}
	}
}
