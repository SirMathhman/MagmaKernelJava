package com.meti;

public interface Cache {
	void append(int priority, String content);

	String render();
}
