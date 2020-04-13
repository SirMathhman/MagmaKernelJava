package com.meti.parse.operate;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;

public class QuantityNode implements Node {
	private final Node value;

	public QuantityNode(Node value) {
		this.value = value;
	}

	@Override
	public Collection<Node> structures() {
		return value.structures();
	}

	@Override
	public String render(Cache cache) {
		return "(" + value.render(cache) + ")";
	}
}
