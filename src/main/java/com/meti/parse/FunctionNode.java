package com.meti.parse;

import com.meti.resolve.Type;

import java.util.Map;
import java.util.stream.Collectors;

public class FunctionNode implements Node {
	private final Node content;
	private final String name;
	private final Map<String, Type> parameters;
	private final Type returnType;

	public FunctionNode(Map<String, Type> parameters, Type returnType, String name, Node content) {
		this.parameters = parameters;
		this.returnType = returnType;
		this.name = name;
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
