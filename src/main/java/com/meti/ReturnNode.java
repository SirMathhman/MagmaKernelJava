package com.meti;

public class ReturnNode implements Node {
	private final Node node;

	public ReturnNode(Node node) {
		this.node = node;
	}

	@Override
	public boolean hasStructure() {
		return node.hasStructure();
	}

	@Override
	public String render(Cache cache) {
		return "return " + node.render(cache) + ";";
	}
}
