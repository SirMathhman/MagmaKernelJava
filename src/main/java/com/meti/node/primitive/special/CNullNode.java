package com.meti.node.primitive.special;

import com.meti.node.Node;

public class CNullNode implements Node {
	public static final Node INSTANCE = new CNullNode();

	@Override
	public String render() {
		return "NULL";
	}
}
