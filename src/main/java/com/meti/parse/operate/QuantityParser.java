package com.meti.parse.operate;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

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
