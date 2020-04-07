package com.meti;

import java.util.Map;
import java.util.stream.Collectors;

public class Function implements Renderable {
	private final Renderable content;
	private final String name;
	private final Map<String, ? extends Type> parameters;
	private final Type returnType;

	public Function(String name, Type returnType, Map<String, ? extends Type> parameters, Renderable content) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
		this.content = content;
	}

	@Override
	public String render() {
		String paramString = renderParamString();
		return returnType.render(name + paramString) + content.render();
	}

	private String renderParamString() {
		return parameters.keySet()
				.stream()
				.map(this::renderParameter)
				.collect(Collectors.joining(",", "(", ")"));
	}

	private String renderParameter(String s) {
		Type type = parameters.get(s);
		return type.render(s);
	}
}
