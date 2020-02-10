package com.meti.node.transform;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.node.Node;

import java.util.Optional;

public class QuantityParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		return Optional.of(content)
				.map(String::trim)
				.filter(s -> s.startsWith("("))
				.filter(s -> s.endsWith(")"))
				.map(s -> s.substring(1, s.length() - 1))
				.map(compiler::parse)
				.map(QuantityNode::new);
	}
}
