package com.meti.compile;

import java.util.List;
import java.util.stream.Collectors;

final class BlockType implements Type {
	private final List<Type> parameters;
	private final Type returnType;

	BlockType(List<Type> parameters, Type returnType) {
		this.parameters = parameters;
		this.returnType = returnType;
	}

	@Override
	public String render(String name) {
		String result = parameters.stream()
				.sorted()
				.map(Type::render)
				.map(String::trim)
				.collect(Collectors.joining(",", "(", ")"));
		return returnType.render("(*" + name + ")") + result;
	}
}
