package com.meti.render.block;

import com.meti.render.Renderable;
import com.meti.type.Type;

import java.util.Map;
import java.util.stream.Collectors;

public class Structure implements Renderable {
	private final Map<String, ? extends Type> fields;
	private final String name;

	public Structure(String name, Map<String, ? extends Type> fields) {
		this.name = name;
		this.fields = fields;
	}

	@Override
	public String render() {
		String renderedFields = renderFields();
		return "struct " + name + renderedFields;
	}

	private String renderFields() {
		return fields.keySet()
				.stream()
				.map(this::renderField).map(s -> s + ";")
				.collect(Collectors.joining("", "{", "}"));
	}

	private String renderField(String s) {
		Type type = fields.get(s);
		return type.render(s);
	}
}
