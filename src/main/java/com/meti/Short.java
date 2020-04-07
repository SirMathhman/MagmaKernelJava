package com.meti;

public class Short implements Renderable {
	private final short value;

	public Short(short value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
