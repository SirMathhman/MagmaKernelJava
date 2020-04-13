package com.meti;

import java.util.Collection;
import java.util.Collections;

public class EmptyNode implements Node {
	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		return "";
	}
}
