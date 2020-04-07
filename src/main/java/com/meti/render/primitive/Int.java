package com.meti.render.primitive;

import com.meti.render.Renderable;

public class Int implements Renderable {
	private final int value;

	public Int(int value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
