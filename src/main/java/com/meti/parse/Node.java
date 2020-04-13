package com.meti.parse;

import com.meti.data.Cache;

import java.util.Collection;

public interface Node {
	Collection<Node> structures();

	String render(Cache cache);
}
