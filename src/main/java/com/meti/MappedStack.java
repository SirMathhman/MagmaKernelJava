package com.meti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MappedStack implements Stack {
	private Scope current = new MappedScope("root", null);

	@Override
	public Scope current() {
		return current;
	}

	@Override
	public Scope enter(String name, Type type) {
		Scope child = define(name, type);
		current = child;
		return child;
	}

	@Override
	public Scope define(String name, Type type) {
		return current.create(name, type);
	}

	@Override
	public Optional<Scope> exit() {
		Optional<Scope> parent = current.parent();
		parent.ifPresent(scope -> current = scope);
		return parent;
	}

	@Override
	public Optional<Scope> search(String name) {
		return current.search(name);
	}

	@Override
	public List<String> values() {
		List<String> list = new ArrayList<>();
		Optional<Scope> optional = Optional.of(current);
		while (optional.isPresent()) {
			Scope scope = optional.get();
			optional = scope.parent();
			list.add(scope.name());
		}
		Collections.reverse(list);
		list.remove(0);
		return list;
	}
}
