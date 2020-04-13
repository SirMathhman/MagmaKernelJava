package com.meti.data;

import com.meti.parse.Type;
import com.meti.parse.block.StructType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MappedScope implements Scope {
	private final Map<String, Scope> children = new HashMap<>();
	private final String name;
	private final Scope parent;
	private final Type type;

	public MappedScope(String name, Type type) {
		this(name, type, null);
	}

	private MappedScope(String name, Type type, Scope parent) {
		this.name = name;
		this.type = type;
		this.parent = parent;
	}

	@Override
	public Map<String, Type> flattenedParameters() {
		Map<String, Type> copy = new HashMap<>();
		if (type instanceof StructType) copy.putAll(((StructType) type).parameters());
		if (null != parent) copy.putAll(parent.flattenedParameters());
		return copy;
	}

	@Override
	public Optional<Scope> child(String name) {
		return Optional.ofNullable(children.get(name));
	}

	@Override
	public Optional<Scope> search(String name) {
		if (children.containsKey(name)) {
			return Optional.ofNullable(children.get(name));
		} else if (null != parent) {
			return parent.search(name);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Scope create(String name, Type type) {
		if (children.containsKey(name)) throw new IllegalArgumentException(name + " is already defined!");
		Scope scope = new MappedScope(name, type, this);
		children.put(name, scope);
		return scope;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Optional<Scope> parent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public Type type() {
		return type;
	}
}
