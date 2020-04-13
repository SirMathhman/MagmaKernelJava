package com.meti;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ConstructNode implements Node {
	private final Collection<? extends Node> children;

	public ConstructNode(Collection<? extends Node> children) {
		this.children = children;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return children.stream()
				.map(node -> node.render(cache))
				.collect(Collectors.joining(",", "{", "}"));
	}
}
