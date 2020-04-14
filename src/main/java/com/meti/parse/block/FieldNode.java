package com.meti.parse.block;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;
import java.util.Collections;

public class FieldNode implements Node {
	private final String child;
	private final Node parent;

	public FieldNode(Node parent, String child) {
		this.parent = parent;
		this.child = child;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return parent.render(cache) + "." + child;
	}
}
