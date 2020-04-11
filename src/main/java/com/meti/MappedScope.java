package com.meti;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MappedScope implements Scope {
	private final Map<String, Scope> children = new HashMap<>();
	private final Instance instance;
	private final String name;
	private final Scope parent;

	public MappedScope() {
		this(null, null, null);
	}

	public MappedScope(String name, Instance instance, Scope parent) {
		this.name = name;
		this.instance = instance;
		this.parent = parent;
	}

	@Override
	public Scope define(String name, Instance instance) {
		if (isDefined(name)) throw new IllegalArgumentException(name + " is already defined.");
		Scope scope = new MappedScope(name, instance, this);
		children.put(name, scope);
		return scope;
	}

	@Override
	public Optional<Scope> getChild(String name) {
		return Optional.ofNullable(children.get(name));
	}

	@Override
	public Instance getInstance() {
		return instance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Optional<Scope> getParent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public boolean isDefined(String name) {
		return getChild(name).isPresent() ||
		       getParent()
				       .map(scope -> scope.isDefined(name))
				       .orElse(false);
	}
}
