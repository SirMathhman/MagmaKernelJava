package com.meti.resolve;

public interface Instance {
	static Instance supplied(Type type) {
		return () -> type;
	}

	Type build();
}
