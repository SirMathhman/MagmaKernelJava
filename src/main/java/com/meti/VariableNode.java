package com.meti;

import java.util.Collection;
import java.util.Collections;

public class VariableNode extends SimpleNode {
	private final String value;

	public VariableNode(String value) {
		this.value = value;
	}

	@Override
	public Collection<CacheUpdate> toUpdates() {
		return Collections.singleton(new LineUpdate(value));
	}
}
