package com.meti.node.declare;

import com.meti.node.Node;

public class CAssignNode implements Node {
	private final Node from;
	private final Node to;

	public CAssignNode(Node to, Node from) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String render() {
		return to.render() + "=" + from.render() + ";";
	}
}
