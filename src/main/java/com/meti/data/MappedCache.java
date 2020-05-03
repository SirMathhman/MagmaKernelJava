package com.meti.data;

import com.meti.parse.Node;

import java.util.*;
import java.util.stream.Collectors;

public class MappedCache implements DataCache {
	private final Map<Integer, List<Node>> children = new HashMap<>();

	@Override
	public void add(int priority, Node value) {
		if (!children.containsKey(priority)) {
			children.put(priority, new ArrayList<>());
		}
		children.get(priority).add(value);
	}

	@Override
	public String render() {
		return children.keySet()
				.stream()
				.sorted()
				.map(children::get)
				.flatMap(Collection::stream)
				.map(Node::render)
				.collect(Collectors.joining());
	}
}
