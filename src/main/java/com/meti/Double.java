package com.meti;

public class Double implements Renderable {
	private final double value;

	public Double(double value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
