package com.meti.node;

public interface DefaultType extends Type {
	Node defaultValue();

	@Override
	default boolean isInstanceOf(Type type) {
		return false;
	}

	@Override
	default String toMagmaString() {
		return "";
	}
}
