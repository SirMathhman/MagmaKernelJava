package com.meti.render;

public class Variable implements Renderable {
	private final String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public String render() {
		return name;
	}
}
