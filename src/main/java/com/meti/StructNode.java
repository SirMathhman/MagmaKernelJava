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
	public boolean hasStructure() {
		return true;
	}

	@Override
	public String render(Cache cache) {
		if (!(type instanceof StructType)) throw new IllegalArgumentException(type + " is not a structure.");
		StructType cast = (StructType) this.type;
		if (content.hasStructure()) {
			cache.append(0, cast.renderStruct(name));
			String header = cast.renderHeader(implName());
			String footer = content.render(cache);
			cache.append(1, header + footer);
			return implName();
		} else {
			String header = cast.renderHeader(implName());
			String footer = content.render(cache);
			cache.append(1, header + footer);
			return implName();
		}
	}

	private String implName() {
		return name + "_";
	}
}
