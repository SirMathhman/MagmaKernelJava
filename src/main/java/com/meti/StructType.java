package com.meti;

public interface StructType extends Type {
	void appendParameter(String name, Type type);

	Node renderConstruction();

	String renderHeader(String name);

	String renderStruct(String name);
}
