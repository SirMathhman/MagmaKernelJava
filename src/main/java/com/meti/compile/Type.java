package com.meti.compile;

public interface Type {
	default String render() {
		return render("");
	}

	String render(String name);
}
