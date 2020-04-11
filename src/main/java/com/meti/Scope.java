package com.meti;

import java.util.Optional;

public interface Scope {
	Scope define(String name, Instance instance);

	Optional<Scope> getChild(String name);

	Instance getInstance();

	String getName();

	Optional<Scope> getParent();

	boolean isDefined(String name);
}
