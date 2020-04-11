package com.meti;

public class StructNode implements Node {
	private final Node content;
	private final String name;
	private final Type type;

	public StructNode(String name, Type type, Node content) {
		this.name = name;
		this.type = type;
		this.content = content;
	}

	@Override
	public String render(Cache cache) {
		if (!(type instanceof StructType)) throw new IllegalArgumentException(type + " is not a structure.");
		String header = ((StructType) type).renderHeader(implName());
		String footer = content.render(cache);
		cache.append(1, header + footer);
		return implName();
	}

	private String implName() {
		return name + "_";
	}
}
