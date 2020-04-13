package com.meti;

import java.util.Collection;
import java.util.Collections;

public class VariableNode implements Node {
	private final String name;

	public VariableNode(String name) {
		this.name = name;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return name;
	}
}
