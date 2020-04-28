package com.meti.parse;

public interface Node {
	Node EMPTY = () -> "";

	static Node supplied(String value) {
		return () -> value;
	}

	String render();
}
