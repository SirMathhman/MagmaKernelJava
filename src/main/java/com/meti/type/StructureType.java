package com.meti.type;

public class StructureType implements Type {
	private final String name;

	public StructureType(String name) {
		this.name = name;
	}

	@Override
	public String render(String name) {
		return "struct " + this.name + " " + name;
	}
}
