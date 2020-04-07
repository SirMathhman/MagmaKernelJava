package com.meti.render;

import com.meti.type.Type;

public class Declare implements Renderable {
	private final String name;
	private final Type type;

	public Declare(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String render() {
		return type.render(name) + ";";
	}
}
