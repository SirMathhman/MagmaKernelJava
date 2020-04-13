package com.meti;

import com.google.inject.Inject;

import java.util.Optional;

public class VariableParser implements Parser {
	private final Stack stack;

	@Inject
	public VariableParser(Stack stack) {
		this.stack = stack;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int period = content.indexOf('.');
		if (-1 == period) {
			return stack.search(content)
					.map(Scope::name)
					.map(VariableNode::new);
		} else {
			String before = content.substring(0, period);
			String after = content.substring(period + 1);
			Scope scope = stack.search(before)
					.orElseThrow(() -> new IllegalStateException(before + " is not defined."));
			Optional<Scope> child = scope.child(after);
			if (child.isEmpty()) throw new IllegalArgumentException(after + " is not a child of " + before);
			return Optional.of(new FieldNode(before, after));
		}
	}
}
