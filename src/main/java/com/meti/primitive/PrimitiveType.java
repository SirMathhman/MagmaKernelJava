package com.meti.primitive;

import com.meti.parse.Node;
import com.meti.parse.Type;

public enum PrimitiveType implements Type {
	SHORT("short", null),
	INT("int", IntNode.DEFAULT),
	LONG("long", null),
	FLOAT("float", null),
	DOUBLE("double", null),
	CHAR("char", null),
	VOID("void", null);

	private final String name;
	private final Node value;

	PrimitiveType(String name, Node value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Node defaultValue() {
		if (null == value) throw new IllegalArgumentException("Cannot locate default value of type: " + name);
		return value;
	}

	@Override
	public String render(String name) {
		return this.name + " " + name;
	}
}
