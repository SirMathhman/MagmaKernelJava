package com.meti;

public class PointerType implements Type {
	private final Type type;

	public PointerType(Type type) {
		this.type = type;
	}

	@Override
	public String render(String name) {
		return type.render("*" + name);
	}
}
