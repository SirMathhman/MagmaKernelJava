package com.meti.parse.util;

import com.meti.data.Cache;
import com.meti.parse.Node;

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
