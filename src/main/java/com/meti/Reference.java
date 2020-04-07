package com.meti;

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
