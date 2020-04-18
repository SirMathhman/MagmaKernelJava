package com.meti.compile;

import java.util.Optional;

public class Cache {
	private String name = null;

	public Optional<String> pull() {
		Optional<String> optional = Optional.ofNullable(this.name);
		this.name = null;
		return optional;
	}

	public void push(String name) {
		if (null == this.name) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Name has not been popped yet.");
		}
	}
}
