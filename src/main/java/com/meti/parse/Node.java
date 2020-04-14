package com.meti.parse;

import com.meti.data.Cache;

import java.util.Collection;

public interface Node {
	String render(Cache cache);

	Collection<Node> structures();
}
