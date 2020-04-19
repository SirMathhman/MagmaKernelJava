package com.meti.compile;

import java.util.Collection;
import java.util.stream.Collectors;

final class BlockType implements Type {
	private final Collection<Type> parameters;
	private final Type returnType;

	BlockType(Collection<Type> parameters, Type returnType) {
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
