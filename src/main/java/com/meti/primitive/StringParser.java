package com.meti.primitive;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

import java.util.Optional;

public class StringParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("\"") && content.endsWith("\"")) {
			String format = content.substring(1, content.length() - 1);
			return Optional.of(new StringNode(format));
		}
		return Optional.empty();
	}
}
