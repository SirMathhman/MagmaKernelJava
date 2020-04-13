package com.meti.parse.util;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;
import java.util.Collections;

public final class NullNode implements Node {
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
