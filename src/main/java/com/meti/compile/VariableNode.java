package com.meti.compile;

final class VariableNode implements Node {
	private final String content;

	public VariableNode(String content) {
		this.content = content;
	}

	@Override
	public String render() {
		return content;
	}
}
