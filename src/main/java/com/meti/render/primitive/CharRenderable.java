package com.meti.render.primitive;

import com.meti.render.Renderable;

public class CharRenderable implements Renderable {
	private final char value;

	public CharRenderable(char value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "'" + value + "'";
	}
}
