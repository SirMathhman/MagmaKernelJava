package com.meti.render.primitive;

import com.meti.render.Renderable;

public class Long implements Renderable {
	private final long value;

	public Long(long value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}