package com.meti;

import java.util.Optional;

public class ReturnParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("return")) {
			Node node = compiler.parse(content.substring(6).trim());
			return Optional.of(new ReturnNode(node));
		}
		return Optional.empty();
	}
}
