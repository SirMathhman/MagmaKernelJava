package com.meti;

import java.util.Collection;

public class CastNode implements Node {
	private final Node node;
	private final Type type;

	public CastNode(Type type, Node node) {
		this.node = node;
		this.type = type;
	}

	@Override
	public Collection<Node> structures() {
		return node.structures();
	}

	@Override
	public String render(Cache cache) {
		return "(" + type.render() + ")" + node.render(cache);
	}
}
