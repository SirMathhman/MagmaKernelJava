package com.meti.parse.store;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.data.Scope;
import com.meti.data.Stack;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.block.FieldNode;

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
			return Optional.of(new VariableNode(content));
	/*		if (stack.hasParameter(content)) {
				return Optional.of(new VariableNode(content));
			}
			Optional<Node> node = stack.search(content)
					.map(Scope::name)
					.map(VariableNode::new);
			return node;*/
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
