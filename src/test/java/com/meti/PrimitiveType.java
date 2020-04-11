package com.meti;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public enum PrimitiveType implements Type {
	INT("int");

	private final String name;

	PrimitiveType(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Type> getChildren() {
		return Collections.emptyMap();
	}

	@Override
	public Optional<Type> getReturnType() {
		return Optional.empty();
	}

	@Override
	public String render(String name) {
		return this.name + " " + name;
	}
}
