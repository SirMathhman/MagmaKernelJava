package com.meti.compile;

import java.util.Optional;

public class IntUnit implements Parser, Resolver {
	private static final Type INSTANCE = new IntType();

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		return parse(content)
				.map(IntNode::new);
	}

	private Optional<Integer> parse(String value) {
		try {
			return Optional.of(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		return Optional.of(content)
				.filter("Int"::equals)
				.map(value -> INSTANCE);
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return parse(content).map(value -> INSTANCE);
	}

	private static final class IntNode implements Node {
		private final int value;

		private IntNode(int value) {
			this.value = value;
		}

		@Override
		public String render() {
			return String.valueOf(value);
		}
	}

	private static class IntType implements Type {
		@Override
		public String render(String name) {
			return "int " + name;
		}
	}
}
