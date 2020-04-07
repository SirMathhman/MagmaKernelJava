package com.meti.render.primitive;

import com.meti.render.Renderable;

public class Double implements Renderable {
	private final double value;

	public Double(double value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
