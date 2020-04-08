package com.meti.render.primitive;

import com.meti.render.Renderable;

public class IntRenderable implements Renderable {
	private final int value;

	public IntRenderable(int value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
