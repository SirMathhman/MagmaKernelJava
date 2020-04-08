package com.meti.parse;

import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class VariableParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		return Optional.of(new VariableNode(content.trim()));
	}
}
