package com.meti.node.primitive.strings;

import com.meti.node.Node;

public class CStringNode implements Node {
	private final String value;

	public CStringNode(String value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "\"" + value + "\"";
	}
}
