package com.meti.compile;

import java.util.Optional;

public class ReturnParser implements Parser {

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("return")) {
			String result = content.substring(6).trim();
			Node value = compiler.parse(result);
			return Optional.of(new ReturnNode(value));
		}
		return Optional.empty();
	}

	private static final class ReturnNode implements Node {
		private final Node value;

		private ReturnNode(Node value) {
			this.value = value;
		}

		@Override
		public String render() {
			return "return " + value.render() + ";";
		}
	}
}
