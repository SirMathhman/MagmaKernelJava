package com.meti.parse.block;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.Collection;

public class ReturnNode implements Node {
	private final Node node;

	public ReturnNode(Node node) {
		this.node = node;
	}

	@Override
	public Collection<Node> structures() {
		return node.structures();
	}

	@Override
	public String render(Cache cache) {
		return "return " + node.render(cache) + ";";
	}
}
