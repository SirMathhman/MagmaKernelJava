package com.meti.parse.store;

import com.meti.data.Cache;
import com.meti.parse.Node;

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
