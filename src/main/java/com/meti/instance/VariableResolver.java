package com.meti.instance;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.Instance;
import com.meti.Resolver;
import com.meti.Scope;

import java.util.Optional;

public class VariableResolver implements Resolver {
	private final Scope scope;

	@Inject
	public VariableResolver(Scope scope) {
		this.scope = scope;
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return scope.search(content);
	}
}
