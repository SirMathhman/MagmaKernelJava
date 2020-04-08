package com.meti.parse;

import com.meti.render.Renderable;

public class ReturnRenderable implements Renderable {
	private final Renderable renderable;

	public ReturnRenderable(Renderable renderable) {
		this.renderable = renderable;
	}

	@Override
	public String render() {
		return "return " + renderable.render() + ";";
	}
}
