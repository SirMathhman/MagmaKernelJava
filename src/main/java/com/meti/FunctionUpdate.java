package com.meti;

import java.util.Map;
import java.util.stream.Collectors;

public class FunctionUpdate implements CacheUpdate {
	private final Node content;
	private final String name;
	private final Map<String, Type> parameters;
	private final Type returnType;

	public FunctionUpdate(String name, Map<String, Type> parameters, Type returnType, Node content) {
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.content = content;
	}

	@Override
	public String identifier() {
		return name;
	}

	@Override
	public String render() {
		String paramString = parameters.keySet()
				.stream()
				.map(s -> parameters.get(s).render(s))
				.collect(Collectors.joining(",", "(", ")"));
		String header = returnType.render(name + paramString);
		return header + content.render();
	}
}
