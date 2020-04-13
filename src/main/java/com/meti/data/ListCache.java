package com.meti.data;

import java.util.*;
import java.util.stream.Collectors;

public class ListCache implements Cache {
	private final Map<Integer, List<String>> content;

	public ListCache() {
		this(new HashMap<>());
	}

	private ListCache(Map<Integer, List<String>> content) {
		this.content = content;
	}

	@Override
	public void append(int priority, String content) {
		content(priority).add(content);
	}

	private List<String> content(int priority) {
		if (!content.containsKey(priority)) {
			content.put(priority, new ArrayList<>());
		}
		return content.get(priority);
	}

	@Override
	public String render() {
		return content.keySet()
				.stream()
				.sorted()
				.map(content::get)
				.flatMap(Collection::stream)
				.collect(Collectors.joining());
	}
}
