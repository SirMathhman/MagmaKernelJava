package com.meti;

import com.meti.resolve.Type;

import java.util.Collection;
import java.util.Optional;

public class CompoundResolver implements Resolver {
	private final Collection<Resolver> children;

	public CompoundResolver(Collection<Resolver> children) {
		this.children = children;
	}

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		return children.stream()
				.map(child -> child.resolveName(content, compiler))
				.flatMap(Optional::stream)
				.findFirst();
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return children.stream()
				.map(child -> child.resolveValue(content, compiler))
				.flatMap(Optional::stream)
				.findFirst();
	}
}
