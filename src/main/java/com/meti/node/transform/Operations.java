package com.meti.node.transform;

import com.meti.node.Node;

import java.util.function.Function;

public enum Operations implements Operation {
	ADD();

	private final String from;
	private final String to;

	Operations() {
		this.from = "+";
		this.to = "+";
	}

	@Override
	public boolean isPresent(String content) {
		return content.contains(from);
	}

	@Override
	public String render(Node node0, Node node1) {
		return node0.render() + to + node1.render();
	}

	@Override
	public Node toNode(String content, Function<? super String, ? extends Node> parser) {
		int fromIndex = content.indexOf(from);
		String before = content.substring(0, fromIndex).trim();
		String after = content.substring(fromIndex + 1).trim();
		Node beforeNode = parser.apply(before);
		Node afterNode = parser.apply(after);
		return new OperationNode(beforeNode, afterNode, this);
	}
}