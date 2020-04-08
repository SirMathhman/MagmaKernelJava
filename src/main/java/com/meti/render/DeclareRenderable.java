package com.meti.render;

import com.meti.type.Type;

public class DeclareRenderable implements Renderable {
	private final String name;
	private final Type type;

	public DeclareRenderable(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String render() {
		return type.render(name) + ";";
	}
}
