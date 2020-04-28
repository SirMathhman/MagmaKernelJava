package com.meti.resolve;

public interface Type {
	static Type supplied(String value) {
		return name -> value + " " + name;
	}

	default String render() {
		return render("");
	}

	String render(String name);
}
