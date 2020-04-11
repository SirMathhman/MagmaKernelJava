package com.meti;

import java.util.Optional;

public class DeclareNode implements Node {
	private final Node init;
	private final String name;
	private final Type type;

	public DeclareNode(String name, Type type) {
		this(name, type, null);
	}

	public DeclareNode(String name, Type type, Node init) {
		this.name = name;
		this.type = type;
		this.init = init;
	}

	@Override
	public Optional<String> render(Cache cache) {
		String header = renderHeader();
		String footer = renderFooter(cache);
		return Optional.of(header + footer + ";");
	}

	private String renderHeader() {
		return type.render(name);
	}

	private String renderFooter(Cache cache) {
		return init().flatMap(node -> node.render(cache))
				.map(value -> "=" + value)
				.orElse("");
	}

	private Optional<Node> init() {
		return Optional.of(init);
	}
}
