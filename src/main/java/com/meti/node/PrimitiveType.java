package com.meti.node;

public abstract class PrimitiveType extends ValueType {
	@Override
	public boolean isInstanceOf(Type type) {
		return this.equals(type);
	}
}
