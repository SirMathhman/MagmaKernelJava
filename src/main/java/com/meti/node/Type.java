package com.meti.node;

public interface Type {
	boolean isInstanceOf(Type type);

	boolean isFunctional();

	String render();

	String render(String name);

	String toMagmaString();
}
