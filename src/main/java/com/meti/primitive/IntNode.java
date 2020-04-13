package com.meti.primitive;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;
import java.util.Collections;

public class IntNode implements Node {
	public static final Node DEFAULT = new IntNode(0);
	private final int value;

	public IntNode(int value) {
		this.value = value;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return String.valueOf(value);
	}
}
