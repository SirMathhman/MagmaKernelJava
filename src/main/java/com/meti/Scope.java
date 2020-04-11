package com.meti;

import java.util.Optional;

public interface Scope {
	Scope define(String name, Type type);

	Optional<Scope> getChild(String name);

	Type getType();

	String getName();

	Optional<Scope> getParent();

	boolean isDefined(String name);
}
