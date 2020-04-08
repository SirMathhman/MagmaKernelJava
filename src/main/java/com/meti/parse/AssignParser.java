package com.meti.parse;

import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class AssignParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = content.indexOf('=');
		if (equals == -1) return Optional.empty();
		String to = content.substring(0, equals);
		String from = content.substring(equals + 1);
		Node toNode = compiler.parse(to);
		Node fromNode = compiler.parse(from);
		return Optional.of(new AssignNode(toNode, fromNode));
	}
}
