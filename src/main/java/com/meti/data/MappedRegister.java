package com.meti.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MappedRegister implements Register {
	private final Map<String, Object> values;

	public MappedRegister() {
		this(new HashMap<>());
	}

	public MappedRegister(Map<String, Object> values) {
		this.values = values;
	}

	@Override
	public boolean has(String name) {
		return values.containsKey(name);
	}

	@Override
	public <T> Optional<T> peek(String name, Class<T> clazz) {
		return Optional.ofNullable(values.get(name)).map(clazz::cast);
	}

	@Override
	public <T> Optional<T> poll(String name, Class<T> clazz) {
		if (values.containsKey(name)) {
			return Optional.of(values.remove(name)).map(clazz::cast);
		}
		return Optional.empty();
	}

	@Override
	public void set(String name, Object value) {
		values.put(name, value);
	}
}
