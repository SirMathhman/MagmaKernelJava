package com.meti;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionType implements Type {
	private final List<? extends Type> paramTypes;
	private final Type returnType;

	public FunctionType(Type returnType, List<? extends Type> paramTypes) {
		this.returnType = returnType;
		this.paramTypes = paramTypes;
	}

	@Override
	public String render(String name) {
		String typeString = renderTypes();
		return returnType.render("(*" + name + ")" + typeString);
	}

	private String renderTypes() {
		return paramTypes.stream()
				.map(Type::render)
				.collect(Collectors.joining(",", "(", ")"));
	}
}
