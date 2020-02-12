package com.meti.node.primitive.doubles;

import com.meti.node.Node;

public class CDoubleNode implements Node {
	private final double value;

	public CDoubleNode(double value) {
		this.value = value;
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
