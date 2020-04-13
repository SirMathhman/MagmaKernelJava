package com.meti;

public class NativeStructType implements Type {
	private final String name;

	public NativeStructType(String name) {
		this.name = name;
	}

	@Override
	public Node defaultValue() {
		throw new UnsupportedOperationException("Not implemented yet, requires knowledge of arguments");
	}

	@Override
	public String render(String name) {
		return "struct " + this.name + " " + name;
	}
}
