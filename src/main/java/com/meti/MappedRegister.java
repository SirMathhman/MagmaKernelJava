package com.meti;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MappedRegister implements Register {
	private final Map<String, Object> values = new HashMap<>();

	@Override
	public <T> Optional<T> peek(String name) {
		return Optional.ofNullable(values.get(name))
				.map(o -> (T) o);
	}

	@Override
	public boolean has(String name) {
		return values.containsKey(name);
	}

	@Override
	public <T> Optional<T> poll(String name) {
		if (values.containsKey(name)) {
			return Optional.of(values.remove(name))
					.map(o -> (T) o);
		}
		return Optional.empty();
	}

	@Override
	public void set(String name, Object value) {
		values.put(name, value);
	}
}
