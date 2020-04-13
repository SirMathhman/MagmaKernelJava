package com.meti.primitive;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

import java.util.Optional;

public class IntParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		try {
			int value = Integer.parseInt(content);
			return Optional.of(new IntNode(value));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}
