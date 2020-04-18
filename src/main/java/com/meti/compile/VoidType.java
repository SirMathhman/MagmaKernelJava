package com.meti.compile;

public class VoidType implements Type {
	public static final Type INSTANCE = new VoidType();

	@Override
	public String render(String name) {
		return "void " + name;
	}
}
