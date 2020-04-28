package com.meti;

import com.meti.resolve.Instance;

import java.util.Collection;
import java.util.Optional;

public class CompoundResolver implements Resolver {
	private final Collection<Resolver> children;

	public CompoundResolver(Collection<Resolver> children) {
		this.children = children;
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return children.stream()
				.map(child -> child.resolveName(content, compiler))
				.flatMap(Optional::stream)
				.findFirst();
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return children.stream()
				.map(child -> child.resolveValue(content, compiler))
				.flatMap(Optional::stream)
				.findFirst();
	}
}
