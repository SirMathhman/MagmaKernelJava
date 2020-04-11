package com.meti;

public class IntNode implements Node {
	private final int value;

	public IntNode(int value) {
		this.value = value;
	}

	@Override
	public boolean hasStructure() {
		return false;
	}

	@Override
	public String render(Cache cache) {
		return String.valueOf(value);
	}
}