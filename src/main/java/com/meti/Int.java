package com.meti;

public class Int implements Renderable {
	private final int value;

	public Int(int value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
