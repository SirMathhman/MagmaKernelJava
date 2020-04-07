package com.meti.render;

public class Operation implements Renderable {
	private final String operation;
	private final Renderable value0;
	private final Renderable value1;

	public Operation(String operation, Renderable value0, Renderable value1) {
		this.operation = operation;
		this.value0 = value0;
		this.value1 = value1;
	}

	@Override
	public String render() {
		return value0.render() + operation + value1.render();
	}
}
