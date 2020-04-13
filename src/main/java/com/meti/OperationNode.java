package com.meti;

import java.util.Collection;
import java.util.HashSet;

public class OperationNode implements Node {
	private final String operation;
	private final Node value0;
	private final Node value1;

	public OperationNode(Node value0, Node value1, String operation) {
		this.value0 = value0;
		this.value1 = value1;
		this.operation = operation;
	}

	@Override
	public Collection<Node> structures() {
		Collection<Node> set = new HashSet<>();
		set.addAll(value0.structures());
		set.addAll(value1.structures());
		return set;
	}

	@Override
	public String render(Cache cache) {
		return value0.render(cache) + "=" + value1.render(cache);
	}
}
