package com.meti.node.transform;

import com.meti.Compiler;
import com.meti.Resolver;
import com.meti.node.Type;

import java.util.Optional;

public class QuantityResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.of(content)
				.map(String::trim)
				.filter(s -> s.startsWith("("))
				.filter(s -> s.endsWith(")"))
				.map(s -> s.substring(1, s.length() - 1))
				.map(compiler::resolveValue);
	}
}
