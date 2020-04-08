package com.meti;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scope {
	private final Map<String, Scope> children;
	private final Instance instance;
	private final Scope parent;

	public Scope() {
		this(null, null);
	}

	public Scope(Scope parent, Instance instance) {
		this(new HashMap<>(), parent, instance);
	}

	public Scope(Map<String, Scope> children, Scope parent, Instance instance) {
		this.children = children;
		this.parent = parent;
		this.instance = instance;
	}

	public void define(String name, Instance instance) {
		Scope scope = new Scope(this, instance);
		this.children.put(name, scope);
	}

	public Optional<Scope> enter(String name) {
		return Optional.ofNullable(children.get(name));
	}

	public Optional<Scope> exit() {
		return Optional.ofNullable(parent);
	}

	public Instance getInstance() {
		return instance;
	}

	public Optional<Instance> search(String trim) {
		return Optional.ofNullable(children.get(trim))
				.map(Scope::getInstance)
				.or(() -> exit().flatMap(scope -> scope.search(trim)));
	}
}
