package com.meti.compile;

import java.util.Optional;

public class Cache {
	private String name = null;
	private Type type = null;

	public boolean hasBlock() {
		return type != null && type instanceof BlockType;
	}

	public Optional<String> pullName() {
		Optional<String> optional = Optional.ofNullable(this.name);
		this.name = null;
		return optional;
	}

	public Optional<Type> pullType() {
		Optional<Type> optional = Optional.ofNullable(type);
		this.type = null;
		return optional;
	}

	public void pushName(String name) {
		this.name = name;
	}

	public void pushType(Type type) {
		this.type = type;
	}
}
