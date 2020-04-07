package com.meti;

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
