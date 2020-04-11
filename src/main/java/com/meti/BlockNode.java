package com.meti;

import java.util.Collection;
import java.util.stream.Collectors;

public class BlockNode implements Node {
	private final Collection<? extends Node> children;

	public BlockNode(Collection<? extends Node> children) {
		this.children = children;
	}

	@Override
	public boolean hasMultiple() {
		return true;
	}

	@Override
	public boolean hasStructure() {
		return children.stream().anyMatch(Node::hasStructure);
	}

	@Override
	public Collection<CacheUpdate> toUpdates() {
		return children.stream()
				.map(Node::toUpdates)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}
}
