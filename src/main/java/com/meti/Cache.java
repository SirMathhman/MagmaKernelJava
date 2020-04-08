package com.meti;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Cache {
	private final LinkedList<Node> nodes = new LinkedList<>();

	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public String render() {
		return nodes.stream()
				.map(Node::renderJoined)
				.collect(Collectors.joining());
	}
}
