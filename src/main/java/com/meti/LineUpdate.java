package com.meti;

import java.util.Collection;
import java.util.Optional;

public class LineUpdate implements CacheUpdate {
	private final String value;

	public LineUpdate(String value) {
		this.value = value;
	}

	@Override
	public Optional<String> identifier() {
		return Optional.empty();
	}

	@Override
	public String render(Collection<String> lines) {
		return value;
	}
}
