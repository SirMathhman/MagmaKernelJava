package com.meti.node.transform;

import com.meti.node.Node;

public class CNotNode implements Node {
	private final Node value;

	public CNotNode(Node value) {
		this.value = value;
	}

	@Override
	public String render() {
		return "!" + value.render();
	}
}
