package com.meti.compile;

import com.google.inject.Inject;

import java.util.Optional;

public class InstanceParser implements Parser {
	private final Cache cache;

	@Inject
	public InstanceParser(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		return Optional.empty();
	}
}
