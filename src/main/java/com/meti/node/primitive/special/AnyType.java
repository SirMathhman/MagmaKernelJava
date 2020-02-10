package com.meti.node.primitive.special;

import com.meti.node.PrimitiveType;
import com.meti.node.Type;

public class AnyType extends PrimitiveType {
	public static final Type INSTANCE = new AnyType();

	@Override
	public String render() {
		return "void";
	}

	@Override
	public String render(String name) {
		return "void " + name;
	}

	@Override
	public String toMagmaString() {
		return "";
	}
}
