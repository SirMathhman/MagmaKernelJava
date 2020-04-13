package com.meti;

import com.google.inject.Inject;

import java.util.Optional;

public class VariableResolver implements Resolver {
	private final Stack stack;

	@Inject
	public VariableResolver(Stack stack) {
		this.stack = stack;
	}

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		return Optional.of(new NativeStructType(content));
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return stack.search(content).map(Scope::type);
	}
}
