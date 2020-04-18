package com.meti.compile;

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
		return Optional.of(content)
				.filter(stack::isDefined)
				.map(VariableNode::new);
	}

	private static final class VariableNode implements Node {
		private final String content;

		private VariableNode(String content) {
			this.content = content;
		}

		@Override
		public String render() {
			return content;
		}
	}
}
