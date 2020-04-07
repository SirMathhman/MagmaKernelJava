package com.meti.type;

public interface Type {
	default String render() {
		return render("").trim();
	}

	String render(String name);
}
