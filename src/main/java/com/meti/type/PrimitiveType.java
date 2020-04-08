package com.meti.type;

public enum PrimitiveType implements Type {
	SHORT("short"),
	INT("int"),
	LONG("long"),
	FLOAT("float"),
	DOUBLE("double"),
	CHAR("char"),
	VOID("void");

	private final String value;

	PrimitiveType(String value) {
		this.value = value;
	}

	@Override
	public String render(String name) {
		return value + " " + name;
	}
}
