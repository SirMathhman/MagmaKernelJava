package com.meti.primitive;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;
import java.util.Collections;

public class StringNode implements Node {
	private final String value;

	public StringNode(String value) {
		this.value = value;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return "\"" + value + "\"";
	}
}
