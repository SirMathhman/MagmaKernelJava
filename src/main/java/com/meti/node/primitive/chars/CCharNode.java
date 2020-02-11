package com.meti.node.primitive.chars;

import com.meti.node.Node;

public class CCharNode implements Node {
	private final char value;

	public CCharNode(char value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "'" + value + "'";
	}
}
