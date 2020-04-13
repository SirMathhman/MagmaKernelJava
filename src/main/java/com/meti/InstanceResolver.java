package com.meti;

import com.google.inject.Inject;

import java.util.Optional;

public class InstanceResolver implements Resolver {
	private final Stack stack;

	@Inject
	public InstanceResolver(Stack stack) {
		this.stack = stack;
	}

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		return Optional.of(new NativeStructType(content));
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}

	private class ScopedType implements Type {
		private final String content;
		private final Scope scope;

		public ScopedType(Scope scope, String content) {
			this.scope = scope;
			this.content = content;
		}

		@Override
		public Node defaultValue() {
			return find().defaultValue();
		}

		private Type find() {
			return scope.search(content)
					.map(Scope::type)
					.filter(type -> !(type instanceof ScopedType))
					.orElse(new NativeStructType(content));
		}

		@Override
		public String render(String name) {
			return find().render(name);
		}
	}
}
