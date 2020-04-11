package com.meti;

import java.util.Collection;

public class BlockItem implements Item {
	private final Collection<? extends Item> children;

	public BlockItem(Collection<? extends Item> children) {
		this.children = children;
	}

	@Override
	public boolean hasMultiple() {
		return true;
	}

	@Override
	public boolean hasStructure() {
		return children.stream().anyMatch(Item::hasStructure);
	}

	@Override
	public Collection<CacheUpdate> render() {
		return null;
	}
}
