package com.meti;

public interface Type {
	Node defaultValue();

	default String render() {
		return render("");
	}

	String render(String name);

	default boolean doesReturnVoid() {
		return false;
	}
}
