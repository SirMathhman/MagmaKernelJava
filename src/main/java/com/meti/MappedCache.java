package com.meti;

import java.util.*;

public class MappedCache implements Cache {
	private final Map<String, String> internalMap;
	private final Collection<String> lines;

	public MappedCache() {
		this(new HashMap<>(), new ArrayList<>());
	}

	public MappedCache(Map<String, String> internalMap, Collection<String> lines) {
		this.internalMap = internalMap;
		this.lines = lines;
	}

	@Override
	public Cache apply(CacheUpdate update) {
		Optional<String> identifier = update.identifier();
		if (identifier.isPresent()) {
			String id = identifier.get();
			if (internalMap.containsKey(id)) {
				throw new IllegalArgumentException(identifier + " has already been defined.");
			} else {
				Map<String, String> copy = new HashMap<>(internalMap);
				copy.put(id, update.render(lines));
				return new MappedCache(copy, lines);
			}
		} else {
			Collection<String> lineCopy = new ArrayList<>(lines);
			lineCopy.add(update.render(lines));
			return new MappedCache(internalMap, lineCopy);
		}
	}

	@Override
	public Collection<String> list() {
		return Collections.unmodifiableCollection(lines);
	}

	@Override
	public String render(String identifier) {
		if (!internalMap.containsKey(identifier)) {
			throw new IllegalArgumentException(identifier + " is not defined.");
		}
		return internalMap.get(identifier);
	}
}
