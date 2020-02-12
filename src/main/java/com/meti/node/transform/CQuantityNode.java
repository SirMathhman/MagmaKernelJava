package com.meti.node.transform;

import com.meti.node.Node;

public class CQuantityNode implements Node {
	private final Node value;

	public CQuantityNode(Node value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "(" + value.render() + ")";
	}
}
