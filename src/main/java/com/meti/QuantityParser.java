package com.meti;

import java.util.Optional;

public class QuantityParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("(") && content.endsWith(")")) {
			String valueString = content.substring(1, content.length() - 1);
			Node value = compiler.parse(valueString);
			return Optional.of(new QuantityNode(value));
		}
		return Optional.empty();
	}
}
