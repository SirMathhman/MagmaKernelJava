package com.meti.data;

import com.meti.parse.Type;

import java.util.List;
import java.util.Optional;

public interface Stack {
	Scope current();

	void enter(String name, Type type);

	Scope define(String name, Type type);

	void exit();

	boolean hasParameter(String name);

	Optional<Scope> search(String name);

	List<String> values();
}
