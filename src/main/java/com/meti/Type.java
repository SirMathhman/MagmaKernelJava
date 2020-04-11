package com.meti;

import java.util.Map;
import java.util.Optional;

public interface Type {
	Map<String, Type> getChildren();

	Optional<Type> getReturnType();

	String render(String name);
}
