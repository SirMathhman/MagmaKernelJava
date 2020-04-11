package com.meti;

import java.util.Map;
import java.util.Optional;

public class VoidType implements Type {
	public static final Type INSTANCE = new VoidType();

	private VoidType() {
	}

	@Override
	public Map<String, Type> getChildren() {
		return null;
	}

	@Override
	public Optional<Type> getReturnType() {
		return Optional.empty();
	}

	@Override
	public String render(String name) {
		return "void " + name;
	}
}
