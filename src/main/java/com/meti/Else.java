package com.meti;

public class Else implements Renderable {
	private final Renderable content;

	public Else(Renderable content) {
		this.content = content;
	}

	@Override
	public String render() {
		return "else" + content.render();
	}
}
