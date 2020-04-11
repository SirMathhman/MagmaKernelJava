package com.meti;

public enum PrimitiveType implements Type {
	SHORT("short"),
	INT("int"),
	LONG("long"),
	FLOAT("float"),
	DOUBLE("double"),
	CHAR("char");

	private final String name;

	PrimitiveType(String name) {
		this.name = name;
	}

	@Override
	public String render(String name) {
		return this.name + " " + name;
	}
}
