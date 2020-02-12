package com.meti.node.point;

import com.meti.node.Node;

public class CDereferenceNode implements Node {
	private final Node value;

	public CDereferenceNode(Node value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "*" + value.render();
	}
}
