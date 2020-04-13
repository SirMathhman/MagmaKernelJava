package com.meti.parse.block;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;
import java.util.Collections;

public class FieldNode implements Node {
	private final String name;
	private final String value;

	public FieldNode(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return name + "." + value;
	}
}
