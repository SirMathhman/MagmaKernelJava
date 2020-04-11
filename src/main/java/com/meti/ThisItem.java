package com.meti;

import java.util.Collection;
import java.util.Collections;

public class ThisItem implements Item {
	private final Scope scope;

	public ThisItem(Scope scope) {
		this.scope = scope;
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
		return Collections.singleton(new LineUpdate(scope.getName()));
	}
}
