package com.meti;

public class VoidType implements Type {
	public static final Type INSTANCE = new VoidType();

	private VoidType() {
	}

	@Override
	public String render(String name) {
		return "void " + name;
	}
}
