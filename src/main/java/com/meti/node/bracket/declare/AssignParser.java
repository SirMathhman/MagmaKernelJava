package com.meti.node.bracket.declare;

import com.meti.compile.Compiler;
import com.meti.node.Node;
import com.meti.node.Parser;

import java.util.Optional;

public class AssignParser implements Parser {

	@Override
	public Optional<Node> parse(String value, Compiler compiler) {
		return Optional.of(value)
				.map(String::trim)
				.filter(trim -> trim.contains("="))
				.map(trim -> build(trim, compiler));
	}

	private Node build(String trim, Compiler compiler) {
		int equals = trim.indexOf('=');
		String value0 = trim.substring(0, equals);
		String value1 = trim.substring(equals + 1);
		Node node0 = compiler.parseSingle(value0);
		Node node1 = compiler.parseSingle(value1);
		return new AssignNode(node0, node1);
	}
}