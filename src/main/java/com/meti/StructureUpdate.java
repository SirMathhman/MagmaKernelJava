package com.meti;

import java.util.Map;
import java.util.stream.Collectors;

public class StructureUpdate implements CacheUpdate {
	private final Map<String, Type> fields;
	private final String name;

	public StructureUpdate(String name, Map<String, Type> fields) {
		this.name = name;
		this.fields = fields;
	}

	@Override
	public String identifier() {
		return name;
	}

	@Override
	public String render() {
		String joinedFields = fields.keySet().stream()
				.map(s -> fields.get(s).render(s))
				.map(s -> s + ";")
				.collect(Collectors.joining("", "{", "}"));
		return "struct " + name + joinedFields + ";";
	}
}
