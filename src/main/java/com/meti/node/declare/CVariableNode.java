package com.meti.node.declare;

import com.meti.node.Node;

public class CVariableNode implements Node {
	private final String value;

	public CVariableNode(String value) {
		this.value = value;
	}

	@Override
	public String render() {
		return value;
	}
}
