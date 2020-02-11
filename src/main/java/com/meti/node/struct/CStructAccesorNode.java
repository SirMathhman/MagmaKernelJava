package com.meti.node.struct;

import com.meti.node.Node;
import com.meti.parse.Declaration;

public class CStructAccesorNode implements Node {
	private final String name;
	private final Node node;

	public CStructAccesorNode(Declaration instance, String name) {
		this(instance.toVariable(), name);
	}

	public CStructAccesorNode(Node node, String name) {
		this.name = name;
		this.node = node;
	}

	@Override
	public String render() {
		return node.render() + "." + name;
	}
}
