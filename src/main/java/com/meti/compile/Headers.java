package com.meti.compile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Headers {
	private final Map<Integer, List<String>> items = new HashMap<>();

	public void append(int priority, String value) {
		if (!items.containsKey(priority)) {
			items.put(priority, new ArrayList<>());
		}
		items.get(priority).add(value);
	}

	public String join() {
		return items.keySet()
				.stream()
				.sorted()
				.map(items::get)
				.map(list -> String.join("", list))
				.collect(Collectors.joining(""));
	}
}
