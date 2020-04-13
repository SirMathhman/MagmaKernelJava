package com.meti;

import java.util.Optional;

public interface Register {
	boolean has(String name);

	<T> Optional<T> peek(String name);

	<T> Optional<T> poll(String name);

	void set(String name, Object value);
}
