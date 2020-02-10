package com.meti.node.struct.type;

import com.meti.node.Type;

public interface FunctionType extends Type {
	@Override
	default boolean isInstanceOf(Type type) {
		return false;
	}

	Type returnType();

	@Override
	default String toMagmaString() {
		return "";
	}

}
