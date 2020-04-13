package com.meti;

import java.util.List;
import java.util.Optional;

public interface Stack {
	Scope current();

	Scope enter(String name, Type type);

	Optional<Scope> exit();

	Optional<Scope> search(String name);

	List<String> values();
}
