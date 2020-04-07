package com.meti.render.primitive;

import com.meti.render.Renderable;

public class Short implements Renderable {
	private final short value;

	public Short(short value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
