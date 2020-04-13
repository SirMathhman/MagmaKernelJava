package com.meti.data;

public interface Cache {
	void append(int priority, String content);

	String render();
}
