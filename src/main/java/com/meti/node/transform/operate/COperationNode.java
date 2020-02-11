package com.meti.node.transform.operate;

import com.meti.node.Node;

public class COperationNode implements Node {
	private final Node node0;
	private final Node node1;
	private final Operation operation;

	public COperationNode(Node node0, Operation operation, Node node1) {
		this.node0 = node0;
		this.node1 = node1;
		this.operation = operation;
	}

	@Override
	public String render() {
		return operation.render(node0, node1);
	}
}
