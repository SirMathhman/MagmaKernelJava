package com.meti;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MappedScope implements Scope {
	private final Map<String, Scope> children = new HashMap<>();
	private final Type type;
	private final String name;
	private final Scope parent;

	public MappedScope() {
		this(null, null, null);
	}

	public MappedScope(String name, Type type, Scope parent) {
		this.name = name;
		this.type = type;
		this.parent = parent;
	}

	@Override
	public Scope define(String name, Type type) {
		if (isDefined(name)) throw new IllegalArgumentException(name + " is already defined.");
		Scope scope = new MappedScope(name, type, this);
		children.put(name, scope);
		return scope;
	}

	@Override
	public Optional<Scope> getChild(String name) {
		return Optional.ofNullable(children.get(name));
	}

	@Override
	public Type getType() {
		return type;
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
