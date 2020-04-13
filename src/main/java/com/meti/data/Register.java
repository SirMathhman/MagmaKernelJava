package com.meti.data;

import java.util.Optional;

public interface Register {
	boolean has(String name);

	<T> Optional<T> peek(String name, Class<T> clazz);

	<T> Optional<T> poll(String name, Class<T> clazz);

	void set(String name, Object value);
}
