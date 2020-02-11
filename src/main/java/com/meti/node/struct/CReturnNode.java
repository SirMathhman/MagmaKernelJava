package com.meti.node.struct;

import com.meti.node.Node;

public class CReturnNode implements Node {
	private final Node value;

	public CReturnNode(Node value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "return " + value.render() + ";";
	}
}
