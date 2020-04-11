package com.meti;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class BlockNode implements Node {
	private final Collection<Node> children;

	public BlockNode(Node child) {
		this(Collections.singleton(child));
	}

	public BlockNode(Collection<Node> children) {
		this.children = children;
	}

	@Override
	public boolean hasStructure() {
		return children.stream().anyMatch(Node::hasStructure);
	}

	@Override
	public String render(Cache cache) {
		return children.stream()
				.map(node -> node.render(cache))
				.collect(Collectors.joining("", "{", "}"));
	}
}
