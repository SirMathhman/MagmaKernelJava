package com.meti.render.point;

import com.meti.render.Renderable;

public class Reference implements Renderable {
	private final Renderable renderable;

	public Reference(Renderable renderable) {
		this.renderable = renderable;
	}

	@Override
	public String render() {
		return "&" + renderable.render();
	}
}
