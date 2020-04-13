package com.meti;

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
