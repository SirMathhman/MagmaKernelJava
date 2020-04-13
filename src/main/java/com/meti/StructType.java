package com.meti;

import java.util.Map;

public interface StructType extends Type {
	void appendChild(String name, Type type);

	void appendParameter(String name, Type type);

	Map<String, Type> parameters();

	Node renderConstruction();

	String renderHeader(String name);

	String renderStruct(String name);
}
