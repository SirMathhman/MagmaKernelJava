package com.meti.node.point;

import com.meti.node.Node;

public class CReferenceNode implements Node {
	private final Node value;

	public CReferenceNode(Node value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "&" + value.render();
	}
}
