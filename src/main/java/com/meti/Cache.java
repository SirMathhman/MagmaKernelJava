package com.meti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Cache {
	private final Collection<Node> nodes = new ArrayList<>();

	public Collection<Node> getNodes() {
		return nodes;
	}

	public String render() {
		return nodes.stream()
				.map(Node::renderJoined)
				.collect(Collectors.joining());
	}
}
