package com.meti;

public class Char implements Renderable {
	private final char value;

	public Char(char value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
