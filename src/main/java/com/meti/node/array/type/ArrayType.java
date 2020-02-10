package com.meti.node.array.type;

import com.meti.node.PrimitiveType;
import com.meti.node.Type;

abstract class ArrayType extends PrimitiveType {
	final Type elementType;

	ArrayType(Type elementType) {
		this.elementType = elementType;
	}

	@Override
	public String toMagmaString() {
		return "";
	}

	@Override
	public String render() {
		return elementType.render() + "*";
	}
}
