package com.meti;

import java.util.Collection;

public interface Item {
	boolean hasMultiple();

	boolean hasStructure();

	Collection<CacheUpdate> toUpdates();
}
