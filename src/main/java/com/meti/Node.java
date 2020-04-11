package com.meti;

import java.util.Collection;

public interface Node {
	boolean hasMultiple();

	boolean hasStructure();

	Collection<CacheUpdate> toUpdates();
}
