package com.meti;

import java.util.Collection;

public interface Node {
	Collection<Node> structures();

	String render(Cache cache);
}
