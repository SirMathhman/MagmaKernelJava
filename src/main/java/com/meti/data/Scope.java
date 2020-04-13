package com.meti.data;

import com.meti.parse.Type;

import java.util.Map;
import java.util.Optional;

public interface Scope {
	Map<String, Type> flattenedParameters();

	Optional<Scope> child(String name);

	Optional<Scope> search(String name);

	Scope create(String name, Type type);

	String name();

	Optional<Scope> parent();

	Type type();
}
