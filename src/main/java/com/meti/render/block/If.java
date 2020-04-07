package com.meti.render.block;

import com.meti.render.Renderable;

public class If implements Renderable {
	private final Renderable condition;
	private final Renderable content;

	public If(Renderable condition, Renderable content) {
		this.condition = condition;
		this.content = content;
	}

	@Override
	public String render() {
		return "if(" + condition.render() + ")" + content.render();
	}
}
