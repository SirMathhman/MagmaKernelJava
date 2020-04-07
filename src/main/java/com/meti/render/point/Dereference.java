package com.meti.render.point;

import com.meti.render.Renderable;

public class Dereference implements Renderable {
	private final Renderable value;

	public Dereference(Renderable value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "*" + value.render();
	}
}
