package com.meti;

import java.util.HashMap;
import java.util.Map;

public class MappedCache implements Cache {
	private final Map<String, String> internalMap;

	public MappedCache() {
		this(new HashMap<>());
	}

	public MappedCache(Map<String, String> internalMap) {
		this.internalMap = internalMap;
	}

	@Override
	public Cache apply(CacheUpdate update) {
		String id = update.identifier();
		if (internalMap.containsKey(id)) {
			throw new IllegalArgumentException(id + " has already been defined.");
		} else {
			Map<String, String> copy = new HashMap<>(internalMap);
			copy.put(id, update.render());
			return new MappedCache(copy);
		}
	}

	@Override
	public String render(String identifier) {
		if (!internalMap.containsKey(identifier)) {
			throw new IllegalArgumentException(identifier + " is not defined.");
		}
		return internalMap.get(identifier);
	}
}
