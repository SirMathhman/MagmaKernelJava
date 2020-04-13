package com.meti.data;

import com.meti.parse.Type;

import java.util.*;

public class MappedStack implements Stack {
	private Scope current = new MappedScope("root", null);

	@Override
	public Scope current() {
		return current;
	}

	@Override
	public void enter(String name, Type type) {
		current = define(name, type);
	}

	@Override
	public Scope define(String name, Type type) {
		return current.create(name, type);
	}

	@Override
	public void exit() {
		Optional<Scope> parent = current.parent();
		parent.ifPresent(scope -> current = scope);
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

	@Override
	public boolean hasParameter(String name) {
		Map<String, Type> map = current.flattenedParameters();
		//TODO: for some reason, containsKey doesn't work here???
		return map.containsKey(name);
	}
}
