package com.meti.render;

public class VariableRenderable implements Renderable {
	private final String name;

	public VariableRenderable(String name) {
		this.name = name;
	}

	@Override
	public String render() {
		return name;
	}
}
