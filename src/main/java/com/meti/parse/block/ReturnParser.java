package com.meti.parse.block;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

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
