package com.meti.parse.block;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlockNode implements ParentNode {
	private final List<Node> children;

	public BlockNode(Node child) {
		this(Collections.singleton(child));
	}

	public BlockNode(Collection<Node> children) {
		this.children = new ArrayList<>(children);
	}

	@Override
	public List<Node> children() {
		return children;
	}

	@Override
	public Collection<Node> structures() {
		return children.stream()
				.map(Node::structures)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public String render(Cache cache) {
		return children.stream()
				.map(node -> node.render(cache))
				.collect(Collectors.joining("", "{", "}"));
	}
}
