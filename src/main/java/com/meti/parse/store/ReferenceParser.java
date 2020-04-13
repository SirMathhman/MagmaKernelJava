package com.meti.parse.store;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

import java.util.Optional;

public class ReferenceParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("&")) {
			String value = content.substring(1);
			Node node = compiler.parse(value);
			return Optional.of(new ReferenceNode(node));
		} else {
			return Optional.empty();
		}
	}
}
