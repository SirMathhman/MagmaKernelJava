package com.meti;

import com.google.inject.Inject;

import java.util.Optional;

public class ThisParser implements Parser {
	private final Stack stack;

	@Inject
	public ThisParser(Stack stack) {
		this.stack = stack;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.equals("this")) {
			return Optional.of(new VariableNode(stack.current().name()));
		}
		return Optional.empty();
	}
}
