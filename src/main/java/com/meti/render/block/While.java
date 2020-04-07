package com.meti.render.block;

import com.meti.render.Renderable;

public class While implements Renderable {
	private final Renderable condition;
	private final Renderable content;

	public While(Renderable condition, Renderable content) {
		this.condition = condition;
		this.content = content;
	}

	@Override
	public String render() {
		return "while(" + condition.render() + ")" + content.render();
	}
}
