package com.meti;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionUpdate implements CacheUpdate {
	private final String name;
	private final Map<String, Type> parameters;
	private final Type returnType;

	public FunctionUpdate(String name, Map<String, Type> parameters, Type returnType) {
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
	}

	@Override
	public Optional<String> identifier() {
		return Optional.of(name);
	}

	@Override
	public String render(Collection<String> lines) {
		String header = renderHeader();
		String content = renderContent(lines);
		return header + content;
	}

	private String renderHeader() {
		String paramString = renderParameters();
		return returnType.render(name + paramString);
	}

	private String renderContent(Collection<String> lines) {
		return lines.stream()
				.map(s -> s + ";")
				.collect(Collectors.joining("", "{", "}"));
	}

	private String renderParameters() {
		return parameters.keySet()
				.stream()
				.map(s -> parameters.get(s).render(s))
				.collect(Collectors.joining(",", "(", ")"));
	}
}
