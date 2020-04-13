package com.meti.parse;

public interface Type {
	Node defaultValue();

	default String render() {
		return render("");
	}

	String render(String name);

	default boolean isReturningVoid() {
		return false;
	}
}
