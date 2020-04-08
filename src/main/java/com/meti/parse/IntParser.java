package com.meti.parse;

import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class IntParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		try {
			int value = Integer.parseInt(content);
			return Optional.of(new IntNode(value));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
