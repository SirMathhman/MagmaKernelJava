package com.meti.parse;

public interface Item {
	static Item supplied(Node node) {
		return () -> node;
	}

	Node build();
}
