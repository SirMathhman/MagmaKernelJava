package com.meti.compile;

import java.util.Map;
import java.util.stream.Collectors;

final class BlockType implements Type {
	private final Map<String, Type> parameters;
	private final Type returnType;

	BlockType(Map<String, Type> parameters, Type returnType) {
		this.parameters = parameters;
		this.returnType = returnType;
	}

	@Override
	public String render(String name) {
		String result = parameters.keySet()
				.stream()
				.sorted()
				.map(parameters::get)
				.map(Type::render)
				.map(String::trim)
				.collect(Collectors.joining(",", "(", ")"));
		return returnType.render("(*" + name + ")") + result;
	}
}
