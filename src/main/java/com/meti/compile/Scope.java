package com.meti.compile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scope {
	private final Map<String, Scope> children = new HashMap<>();
	private final String name;
	private final Scope parent;
	private final Type type;

	public Scope(String name, Type type) {
		this(null, name, type);
	}

	public Scope(Scope parent, String name, Type type) {
		this.parent = parent;
		this.name = name;
		this.type = type;
	}

	public Scope define(String name, Type type) {
		Scope scope = new Scope(this, name, type);
		children.put(name, scope);
		return scope;
	}

	public Optional<Scope> exit() {
		return getParent();
	}

	public Optional<Scope> getParent() {
		return Optional.ofNullable(parent);
	}

	public Optional<Scope> find(String content) {
		return Optional.ofNullable(children.get(content))
				.or(() -> getParent().flatMap(parent -> parent.find(content)));
	}

	public Map<String, Scope> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
}
