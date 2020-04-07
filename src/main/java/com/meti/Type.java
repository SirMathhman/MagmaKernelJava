package com.meti;

public interface Type {
	default String render() {
		return render("").trim();
	}

	String render(String name);
}
