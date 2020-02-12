package com.meti.node.primitive.floats;

import com.meti.node.Node;

public class CFloatNode implements Node {
	private final float value;

	public CFloatNode(float value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
