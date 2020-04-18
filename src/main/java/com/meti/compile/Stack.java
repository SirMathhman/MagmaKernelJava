package com.meti.compile;

public class Stack {
	private Scope current = new Scope("root", null);

	public void enter(String name, Type type) {
		current = current.enter(name, type);
	}

	public void exit() {
		current = current.exit().orElse(current);
	}

	public boolean isDefined(String content) {
		return current.find(content).isPresent();
	}
}
