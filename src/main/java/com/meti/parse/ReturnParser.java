package com.meti.parse;

import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class ReturnParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("return")) {
			String trim = content.substring(6).trim();
			Node parse = compiler.parse(trim);
			return Optional.of(new ReturnNode(parse));
		}
		return Optional.empty();
	}
}
