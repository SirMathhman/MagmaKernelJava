package com.meti.node.primitive.ints;

import com.meti.node.PrimitiveType;
import com.meti.node.Type;

public class IntType extends PrimitiveType {
	public static final Type INSTANCE = new IntType();

	@Override
	public String render() {
		return "int";
	}

	@Override
	public String render(String name) {
		return "int " + name;
	}

	@Override
	public String toMagmaString() {
		return "";
	}
}
