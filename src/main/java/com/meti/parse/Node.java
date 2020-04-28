package com.meti.parse;

public interface Node {
	static Node supplied(String value) {
		return () -> value;
	}

	String render();
}
