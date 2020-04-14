package com.meti.parse.block;

import com.meti.parse.Node;
import com.meti.parse.Type;

public class NativeStructType implements Type {
	private final String name;

	@Override
	public String toString() {
		return "NativeStructType{" +
		       "name='" + name + '\'' +
		       '}';
	}

	public NativeStructType(String name) {
		this.name = name;
	}

	@Override
	public Node defaultValue() {
		throw new UnsupportedOperationException("Not implemented yet, requires knowledge of arguments");
	}

	@Override
	public String render(String name) {
		return "struct " + this.name + " " + name;
	}
}
