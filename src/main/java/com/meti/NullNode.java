package com.meti;

import java.util.Collection;
import java.util.Collections;

public class NullNode implements Node {
	public static final Node INSTANCE = new NullNode();

	private NullNode() {
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return "NULL";
	}
}
