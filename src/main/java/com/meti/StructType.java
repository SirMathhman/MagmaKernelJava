package com.meti;

public interface StructType extends Type {
	void appendChild(String name, Type type);

	void appendParameter(String name, Type type);

	Node renderConstruction();

	String renderHeader(String name);

	String renderStruct(String name);
}
