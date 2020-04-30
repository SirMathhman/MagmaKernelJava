package com.meti.parse;

import java.util.Collection;
import java.util.stream.Collectors;

public class BlockNode implements Node {
	private final Collection<Node> children;

	public BlockNode(Collection<Node> children) {
		this.children = children;
	}

	@Override
	public String render() {
		return children.stream()
				.map(Node::render)
				.collect(Collectors.joining("", "{", "}"));
	}
}
