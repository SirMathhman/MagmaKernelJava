package com.meti;

import java.util.Collection;
import java.util.Collections;

public class IntNode implements RenderedNode {
	private final int value;

	public IntNode(int value) {
		this.value = value;
	}

	@Override
	public boolean hasMultiple() {
		return false;
	}

	@Override
	public boolean hasStructure() {
		return false;
	}

	@Override
	public Collection<CacheUpdate> toUpdates() {
		return Collections.singleton(new LineUpdate(render()));
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}
}
