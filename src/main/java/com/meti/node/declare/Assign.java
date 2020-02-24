package com.meti.node.declare;

import com.meti.node.Node;
import com.meti.node.Statement;

public class Assign implements Statement {
	private final Node from;
	private final Node to;

	public Assign(Node to, Node from) {
		this.to = to;
		this.from = from;
	}

	@Override
	public Node build() {
		return new CAssignNode(to, from);
	}
}
