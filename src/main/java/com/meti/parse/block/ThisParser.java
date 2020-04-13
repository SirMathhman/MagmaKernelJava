package com.meti.parse.block;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.data.Stack;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.store.VariableNode;

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
