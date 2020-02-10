package com.meti.node.primitive.doubles;

import com.meti.node.PrimitiveType;
import com.meti.node.Type;

public class DoubleType extends PrimitiveType {
	public static final Type INSTANCE = new DoubleType();

	@Override
	public String toMagmaString() {
		return "Double";
	}

	@Override
	public String render() {
		return "double";
	}

	@Override
	public String render(String name) {
		return "double " + name;
	}
}
