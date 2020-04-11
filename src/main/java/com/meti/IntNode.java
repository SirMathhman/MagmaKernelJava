package com.meti;

import java.util.Optional;

public class IntNode implements Node {
	private final int value;

	public IntNode(int value) {
		this.value = value;
	}

	@Override
	public Optional<String> render(Cache cache) {
		return Optional.of(String.valueOf(value));
	}
}
