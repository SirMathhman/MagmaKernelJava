package com.meti.render;

public class Assign implements Renderable {
	private final Renderable from;
	private final Renderable to;

	public Assign(Renderable to, Renderable from) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String render() {
		return to.render() + "=" + from.render() + ";";
	}
}
