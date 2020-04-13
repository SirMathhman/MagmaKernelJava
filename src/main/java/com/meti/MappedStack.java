package com.meti;

import java.util.Optional;

public class MappedStack implements Stack {
	private Scope current = new MappedScope("root", null);

	@Override
	public Scope enter(String name, Type type) {
		Scope child = current.create(name, type);
		current = child;
		return child;
	}

	@Override
	public Optional<Scope> exit() {
		Optional<Scope> parent = current.parent();
		parent.ifPresent(scope -> current = scope);
		return parent;
	}

	@Override
	public Optional<Scope> search(String name) {
		Optional<Scope> optional = Optional.of(current);
		while (optional.isPresent()) {
			Scope scope = optional.get();
			Optional<Scope> child = scope.child(name);
			if (child.isPresent()) {
				return child;
			} else {
				optional = scope.parent();
			}
		}
		return Optional.empty();
	}
}
