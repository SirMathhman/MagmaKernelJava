package com.meti.resolve;

public interface Type {
	default String render() {
		return render("");
	}

	String render(String name);
}
