package com.meti.compile;

import java.util.Map;
import java.util.stream.Collectors;

public class Function implements Node {
	private final Node content;
	private final String name;
	private final Map<String, Type> parameters;
	private final Type returnType;

	public Function(String name, Map<String, Type> parameters, Type returnType, Node content) {
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.content = content;
	}

	@Override
	public String render() {
		String result = parameters.keySet()
				.stream()
				.map(s -> parameters.get(s).render(s))
				.collect(Collectors.joining(",", "(", ")"));
		return returnType.render(name + result) + content.render();
	}
}
