package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.*;

import java.util.Optional;

public class VariableParser implements Parser {
	private final Accumulator accumulator;
	private final Scope scope;

	@Inject
	public VariableParser(Scope scope, Accumulator accumulator) {
		this.scope = scope;
		this.accumulator = accumulator;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (scope.search(content.trim()).isPresent()) {
			return Optional.of(new VariableNode(content.trim()));
		} else {
			return Optional.empty();
		}
	}
}
