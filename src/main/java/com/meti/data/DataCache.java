package com.meti.data;

import com.meti.parse.Node;

public interface DataCache {
	void add(int priority, Node value);

	String render();
}
