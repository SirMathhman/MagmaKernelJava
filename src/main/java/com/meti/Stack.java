package com.meti;

import java.util.Optional;

public interface Stack {
	Scope enter(String name, Type type);

	Optional<Scope> exit();

	Optional<Scope> search(String name);
}
