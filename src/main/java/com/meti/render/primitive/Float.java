package com.meti.render.primitive;

import com.meti.render.Renderable;

public class Float implements Renderable {
	private final float value;

	public Float(float value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
