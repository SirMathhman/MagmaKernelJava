package com.meti;

import java.util.Optional;

public interface Scope {
	Optional<Scope> child(String name);

	Scope create(String name, Type type);

	String name();

	Optional<Scope> parent();

	Type type();
}
