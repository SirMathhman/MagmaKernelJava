package com.meti.parse.store;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;
import java.util.Collections;

public class ReferenceNode implements Node {
	private final Node value;

	public ReferenceNode(Node value) {
		this.value = value;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return "&" + value.render(cache);
	}
}
