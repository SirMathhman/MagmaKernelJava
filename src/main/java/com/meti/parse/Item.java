package com.meti.parse;

public interface Item {
	Item EMPTY = () -> Node.EMPTY;

	static Item supplied(Node node) {
		return () -> node;
	}

	Node build();
}
